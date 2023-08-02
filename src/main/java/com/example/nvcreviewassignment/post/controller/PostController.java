package com.example.nvcreviewassignment.post.controller;

import com.example.nvcreviewassignment.common.handler.ApiResponse;
import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.post.dto.PostListResponseDto;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import com.example.nvcreviewassignment.post.dto.PostResponseDto;
import com.example.nvcreviewassignment.post.entity.Post;
import com.example.nvcreviewassignment.post.service.PostService;
import com.example.nvcreviewassignment.user.entity.User;
import jakarta.annotation.PostConstruct;
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
    public ResponseEntity<PostListResponseDto> getPostList() {
        PostListResponseDto postList = postService.getPostList();

        return ResponseEntity.ok().body(postList);
    }

    @GetMapping("/page")
    public ResponseEntity<PostListResponseDto> getPostListByPage(@RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        // 페이징 처리: 1: 정렬 기준없이, 쌓인 데이터 순서대로 페이징 처리
        PostListResponseDto postList = postService.getPostListByPage(page, size);

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
