package com.example.nvcreviewassignment.user.controller;

import com.example.nvcreviewassignment.common.handler.ApiResponse;
import com.example.nvcreviewassignment.common.jwt.JwtUtil;
import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void setBeanFactory(BeanFactory context){
        jwtUtil = (JwtUtil)context.getBean(JwtUtil.class);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody AuthRequestDto requestDto) {
        try {
            userService.signup(requestDto);
            return ResponseEntity.status(201).body(new ApiResponse("회원가입 성공", HttpStatus.CREATED.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequestDto loginRequestDto, HttpServletResponse res) {
        userService.login(loginRequestDto);
        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getNickname()));
        return ResponseEntity.ok().body(new ApiResponse("로그인 성공", HttpStatus.CREATED.value()));
    }
}
