package com.entvy.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequestDto {
    private String refreshToken;

    public TokenRequestDto() {}

    public TokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
