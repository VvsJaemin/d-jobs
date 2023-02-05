package com.api.djobs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String username;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;


    public void setUsername(String username) {
        this.username = username;
    }

    @Getter
    @NoArgsConstructor
    public static class TokenRequest{
        private String accessToken;
        private String refreshToken;
    }

}