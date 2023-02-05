package com.api.djobs.user.service;


import com.api.djobs.jwt.util.SecurityUtil;
import com.api.djobs.user.domain.User;
import com.api.djobs.user.dto.UserDto;
import com.api.djobs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public UserDto.UserResponse getMyInfo() {
        return repository.findById(SecurityUtil.getCurrentUserId())
                .map(UserDto.UserResponse::new)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


    @Override
    public boolean checkChangeableAuthority(long userId, int authorityCode) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new InvalidParameterException("해당 id의 유저가 존재하지 않습니다."));
        return authorityCode >= user.getAuthority().getCode();
    }

    @Override
    public void modify(UserDto.UserRequest userRequest) {
        User user = userRequest.toEntity(passwordEncoder);

//        User modifyUser = repository.getById(user.getUserId());
//        modifyUser.changePassword(user.getPassword());
//        modifyUser.changeNickname(user.getNickName());

    }

    @Override
    public void deleteUserRelate(Long userId) {
    }

    @Override
    public void deleteUser(UserDto.UserResponse dto) {
        Long userId = dto.getUserId();
        deleteUserRelate(userId);
        repository.deleteById(userId);
    }

    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String pwd = "";
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }
        return pwd;
    }

    public void updatePassword(String tempPassword, String username) {
        String encodeTempPassword = passwordEncoder.encode(tempPassword);
        User user = repository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
//        user.changePassword(encodeTempPassword);
    }


}