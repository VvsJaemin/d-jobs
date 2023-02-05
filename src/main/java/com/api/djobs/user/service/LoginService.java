package com.api.djobs.user.service;

import com.api.djobs.user.dto.LoginDto;
import com.api.djobs.user.dto.TokenDto;
import com.api.djobs.user.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    UserDto.UserResponse signup(UserDto.UserRequest userRequest);

    TokenDto login(HttpServletRequest request, HttpServletResponse response, LoginDto.Request loginRequestDto);

    TokenDto reIssue(HttpServletRequest request, HttpServletResponse response, TokenDto.TokenRequest tokenRequestDto);

    void logout(String accessToken, String refreshToken);
    boolean checkUsername(String username);
}
