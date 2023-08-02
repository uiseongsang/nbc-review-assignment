package com.example.nvcreviewassignment.post.controller;

import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.post.dto.PostListResponseDto;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import com.example.nvcreviewassignment.post.dto.PostResponseDto;
import com.example.nvcreviewassignment.post.service.PostService;
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

    /**
     * 전체 게시글 조회
     */
    @GetMapping("/all")
    public ResponseEntity<PostListResponseDto> getPostList() {
        PostListResponseDto postList = postService.getPostList();

        return ResponseEntity.ok().body(postList);
    }

    /**
     * 페이지네이션 사용하여 게시글 조회
     * @param page 페이지 시작(0부터 시작)
     * @param size 몇 건을 보여줄건지
     */
    @GetMapping("/page")
    public ResponseEntity<PostListResponseDto> getPostListByPage(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        // 페이징 처리: 1: 정렬 기준없이, 쌓인 데이터 순서대로 페이징 처리
        PostListResponseDto postList = postService.getPostListByPage(page, size);

        return ResponseEntity.ok().body(postList);
    }

    /**
     * 게시글 단건 조회
     * @param id 단건 조회할 게시글 번호
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto res = postService.getPostById(id);

        return ResponseEntity.ok().body(res);
    }

    /**
     * 게시글 작성
     * @param requestDto 작성할 Post body 정보
     * @param userDetails 사용자 정보
     */
    @PostMapping("")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto res = postService.createPost(requestDto, userDetails);
        return ResponseEntity.status(201).body(res);
    }

    /**
     * 게시글 수정
     * @param id 수정할 게시글 번호
     * @param requestDto 수정할 게시글 정보
     * @param userDetails 사용자 정보
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto res = postService.updatePost(id,requestDto,userDetails.getUser());
        return ResponseEntity.ok().body(res);
    }
}
