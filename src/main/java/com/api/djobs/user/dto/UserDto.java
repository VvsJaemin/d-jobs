package com.api.djobs.user.dto;

import com.api.djobs.jwt.util.SecurityUtil;
import com.api.djobs.user.domain.Authority;
import com.api.djobs.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.InvalidParameterException;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserRequest {
        private Long userId;
        private String username;
        private String password;

        private String nickname;

        private Authority authority;

        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .nickname(nickname)
                    .authority(Authority.ROLE_USER)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private Long userId;
        private String username;
        private String nickname;

        public UserResponse(User user) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userInfoModify {
//        @NotBlank(message = "새로운 password값이 필수 있니다.")
//        @Pattern(regexp = "^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
//                message = "비밀번호는 8~20자리의 숫자,문자,특수문자로 이루어져야합니다.")
        private String password;
//        @NotBlank(message = "새로운 passwordCheck값이 필수 입니다.")
        private String checkPassword;
        private String nickname;

        public UserDto.UserRequest changeUserInfo() {
            if (!password.equals(checkPassword)) {
                throw new InvalidParameterException("새로운 비밀번호와 비밀번호 확인 값이 같지 않습니다.");
            }
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserRequest.builder()
                    .userId(currentUserId)
                    .password(password)
                    .nickname(nickname)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class userDelete {
        public static UserDto.UserResponse deleteUserId() {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserResponse.builder()
                    .userId(currentUserId)
                    .build();
        }
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MailTempPwdDto {
        private String toAddress;
        private String title;
        private String message;
        private String fromAddress;
    }



}
