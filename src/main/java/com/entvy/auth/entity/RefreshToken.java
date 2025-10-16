package com.entvy.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    
    @Column(length = 500)
    private String token;
    
    private LocalDateTime expiresAt;

    public RefreshToken(String email, String newRefreshToken, LocalDateTime localDateTime) {
        this.email = email;
        this.token = newRefreshToken;
        this.expiresAt = localDateTime;
    }

    // 선택 : 기기정보, IP, userAgent 등
}
