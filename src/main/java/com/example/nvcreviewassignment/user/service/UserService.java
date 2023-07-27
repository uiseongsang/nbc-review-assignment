package com.example.nvcreviewassignment.user.service;

import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.entity.User;
import com.example.nvcreviewassignment.user.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

public interface UserService {

    /**
     * 회원가입
     *
     * @param requestDto 닉네임,비밀번호,비밀번호확인
     */
    void signup(AuthRequestDto requestDto);

    /**
     * 로그인
     *
     * @param loginRequestDto 로그인할 닉네임이랑 비밀번호
     */
    void login(AuthRequestDto loginRequestDto);
}
