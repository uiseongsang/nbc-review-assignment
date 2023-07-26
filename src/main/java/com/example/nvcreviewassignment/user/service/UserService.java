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
     *
     * @param requestDto
     */
    public void signup(AuthRequestDto requestDto);




}
