package com.example.nvcreviewassignment.user.controller;

import com.example.nvcreviewassignment.common.handler.ApiResponse;
import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.user.dto.AuthRequestDto;
import com.example.nvcreviewassignment.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}
