package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignupRequestDto;
import com.example.demo.entity.Member;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(401).body("Refresh Token이 없습니다.");
        }

        String email = jwtUtil.validateTokenAndGetEmail(refreshToken);
        if(email == null) {
            return ResponseEntity.status(401).body("Refresh Token이 유효하지 않습니다.");
        }

        String newAccessToken = jwtUtil.generateToken(email);
        return ResponseEntity.ok(newAccessToken);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
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

        String accessToken = jwtUtil.generateToken(member.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(member.getEmail());

        // Refresh Token은 HttpOnly 쿠키로 저장
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 토큰 유효기간 7일
//                .maxAge(10) // 테스트용 10초
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