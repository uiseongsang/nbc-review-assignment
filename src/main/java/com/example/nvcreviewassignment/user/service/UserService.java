package com.example.nvcreviewassignment.user.service;

import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.entity.User;
import com.example.nvcreviewassignment.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(AuthRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        Boolean userCheck = userRepository.existsAllByNickname(requestDto.getNickname());

        if(userCheck) {
            throw new IllegalArgumentException("중복된 닉네임입니다");
        }

        if(!requestDto.getPassword().equals(requestDto.getConfirmedPassword())) {
            throw new IllegalArgumentException("확인된 비밀번호가 일치하지 않습니다");
        }

        User user = new User().builder()
                        .nickname(requestDto.getNickname())
                        .password(password)
                        .build();

        userRepository.save(user);
    }
}
