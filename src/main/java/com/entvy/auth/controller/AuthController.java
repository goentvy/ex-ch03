package com.entvy.auth.controller;

import com.entvy.auth.dto.LoginRequestDto;
import com.entvy.auth.dto.SignupRequestDto;
import com.entvy.auth.dto.TokenRequestDto;
import com.entvy.auth.dto.TokenResponseDto;
import com.entvy.auth.entity.Member;
import com.entvy.auth.entity.RefreshToken;
import com.entvy.auth.jwt.JwtUtil;
import com.entvy.auth.repository.MemberRepository;
import com.entvy.auth.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
//    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // JWT 재발급 로직
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissue(@RequestBody TokenRequestDto request) {
        String refreshToken = request.getRefreshToken();

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        if(token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh Token expired");
        }

        String newAccessToken = jwtUtil.createAccessToken(token.getEmail());
        String newRefreshToken = jwtUtil.createRefreshToken(token.getEmail());

        // 기존 토큰 삭제 후 새 토큰 저장
        refreshTokenRepository.deleteByEmail(token.getEmail());
        refreshTokenRepository.save(new RefreshToken(token.getEmail(), newRefreshToken, LocalDateTime.now().plusDays(7)));

        return ResponseEntity.ok(new TokenResponseDto(newAccessToken, newRefreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(401).body("Refresh Token이 없습니다.");
        }
//      DB에서 토큰 검증
        Optional<RefreshToken> tokenEntity = refreshTokenRepository.findByToken(refreshToken);
        if (tokenEntity.isEmpty() || tokenEntity.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(401).body("Refresh Token이 유효하지 않습니다.");
        }

        String email = jwtUtil.validateTokenAndGetEmail(refreshToken);
        if(email == null) {
            return ResponseEntity.status(401).body("Refresh Token이 유효하지 않습니다.");
        }

//      Redis에서 검증
//        String storedToken = redisTemplate.opsForValue().get("refreshToken:" + email);
//        if(!refreshToken.equals(storedToken)) {
//            return ResponseEntity.status(401).body("Refresh Token이 일치하지 않습니다.");
//        }

        String newAccessToken = jwtUtil.createAccessToken(email);
        return ResponseEntity.ok(newAccessToken);
    }

    @PostMapping("/logout")
    @Transactional
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                       HttpServletResponse response) {
        // 로그아웃 시 DB에서 토큰 삭제
        if(refreshToken != null) {
            refreshTokenRepository.deleteByToken(refreshToken);
        }
        // Redis 에서 토큰 삭제
//        if(refreshToken != null) {
//            String email = jwtUtil.validateTokenAndGetEmail(refreshToken);
//            if(email != null) {
//                redisTemplate.delete("refreshToken:" + email);
//            }
//        }
        // 쿠키삭제: maxAge = 0
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0) // 쿠키 삭제
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElse(null);

        if(member == null || !passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(member.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(member.getEmail());

        // Redis 저장
//        redisTemplate.opsForValue().set(
//            "refreshToken:" + member.getEmail(),
//            refreshToken,
//            Duration.ofDays(7)
//        );
        // Refresh Token DB 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setEmail(member.getEmail());
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setExpiresAt(LocalDateTime.now().plusDays(7));
        refreshTokenRepository.save(refreshTokenEntity);

        // Refresh Token은 HttpOnly 쿠키로 저장
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 토큰 유효기간 7일
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(accessToken); // Access Token은 본문으로 반환
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .build();

        memberRepository.save(member);
        return ResponseEntity.ok("회원가입 성공!");
    }
}