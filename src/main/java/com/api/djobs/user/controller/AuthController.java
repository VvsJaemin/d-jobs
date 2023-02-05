package com.api.djobs.user.controller;

import com.api.djobs.user.dto.LoginDto;
import com.api.djobs.user.dto.TokenDto;
import com.api.djobs.user.dto.UserDto;
import com.api.djobs.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;



    @PostMapping("/signup")
    public ResponseEntity<UserDto.UserResponse> signup( @RequestBody UserDto.UserRequest userRequestDto) {
        return ResponseEntity.ok(loginService.signup(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto.Request loginRequestDto) {
//        boolean dup = mailService.isCheckedAuthMail(loginRequestDto.getUsername());

        return ResponseEntity.ok(loginService.login(request, response, loginRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(HttpServletRequest request, HttpServletResponse response, @RequestBody TokenDto.TokenRequest tokenRequestDto) {
        return ResponseEntity.ok(loginService.reIssue(request, response, tokenRequestDto));
    }

    @GetMapping("/check/{username}")
    public ResponseEntity<Boolean> checkUserRequestDto( @PathVariable String username) {
        return ResponseEntity.ok(loginService.checkUsername(username));
    }

//    @DeleteMapping("/logout")
//    public ResponseEntity<ApiResponse> logout(@RequestBody TokenDto.TokenRequest tokenRequestDto) {
//
//        loginService.logout(tokenRequestDto.getAccessToken(), tokenRequestDto.getRefreshToken());
//
//        return ResponseEntity.ok(ApiResponse.success("로그아웃 성공", "성공"));
//    }

}
