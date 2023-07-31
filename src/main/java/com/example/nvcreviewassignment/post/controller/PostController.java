package com.example.nvcreviewassignment.post.controller;

import com.example.nvcreviewassignment.common.handler.ApiResponse;
import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.post.dto.PostListResponseDto;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import com.example.nvcreviewassignment.post.dto.PostResponseDto;
import com.example.nvcreviewassignment.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto postList = postService.getPosts();

        return ResponseEntity.ok().body(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto res = postService.getPostById(id);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto res = postService.createPost(requestDto, userDetails);
        return ResponseEntity.status(201).body(res);
    }
}
