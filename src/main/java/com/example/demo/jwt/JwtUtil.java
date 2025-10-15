package com.example.demo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long REFRESH_TOKEN_EXPIRY = 1000 * 60 * 60 * 24 * 7; // 토큰 유효기간 7일
    private final long ACCESS_TOKEN_EXPIRY = 1000 * 60 * 30; // 토큰 유효기간 30분

    public String createAccessToken(String email) {
        return createToken(email, ACCESS_TOKEN_EXPIRY);
    }
    public String createRefreshToken(String email) {
        return createToken(email, REFRESH_TOKEN_EXPIRY);
    }

    public String createToken(String subject, long expiry) {
        return Jwts.builder()
                .setSubject(subject) // 토큰의 주체 (사용자 식별용)
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(key) // 서명 키
                .compact(); // JWT 문자열로 변환
    }

    public String validateTokenAndGetEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
