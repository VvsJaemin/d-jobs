package com.api.djobs.user.service;


import com.api.djobs.user.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    boolean checkChangeableAuthority(long userId, int authorityCode);

    void modify(UserDto.UserRequest userRequest);

    UserDto.UserResponse getMyInfo();

    void deleteUserRelate(Long userId);

    void deleteUser(UserDto.UserResponse dto);

    String getTempPassword();

    void updatePassword(String tempPassword, String username);

}