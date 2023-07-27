package com.example.nvcreviewassignment.user.service;

import com.example.nvcreviewassignment.common.exception.FailLoginException;
import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.entity.User;
import com.example.nvcreviewassignment.user.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    @Override
    public void signup(AuthRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        Boolean userCheck = userRepository.existsAllByNickname(requestDto.getNickname());

        if (userCheck) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "duplicated.nickname",
                            null,
                            "Duplicated Nickname",
                            Locale.getDefault()
                    )
            );
        }

        if (!requestDto.getPassword().equals(requestDto.getConfirmedPassword())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "not.correct.password.and.confirmed.password",
                            null,
                            "two diff password",
                            Locale.getDefault()
                    )
            );
        }

        User user = new User().builder()
                .nickname(requestDto.getNickname())
                .password(password)
                .build();

        userRepository.save(user);
    }

    @Override
    public void login(AuthRequestDto loginRequestDto) {
        User user = userRepository.findByNickname(loginRequestDto.getNickname()).orElseThrow(
                () -> new FailLoginException(
                        messageSource.getMessage(
                                "not.found.user",
                                null,
                                "Not found user",
                                Locale.getDefault()
                        )
                )
        );

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new FailLoginException(
                    messageSource.getMessage(
                            "not.correct.password",
                            null,
                            "not correct password",
                            Locale.getDefault()
                    )
            );
        }
    }
}
