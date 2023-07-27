package com.example.nvcreviewassignment.user.service;

import com.example.nvcreviewassignment.user.dto.AuthRequestDto;

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
