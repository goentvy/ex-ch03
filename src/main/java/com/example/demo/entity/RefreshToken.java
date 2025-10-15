package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    
    // 선택 : 기기정보, IP, userAgent 등
}
