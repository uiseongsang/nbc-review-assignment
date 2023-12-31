package com.example.nvcreviewassignment.post.service;

import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.post.dto.PostListResponseDto;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import com.example.nvcreviewassignment.post.dto.PostResponseDto;
import com.example.nvcreviewassignment.post.entity.Post;
import com.example.nvcreviewassignment.post.repository.PostRepository;
import com.example.nvcreviewassignment.user.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostListResponseDto getPostList() {
        List<PostResponseDto> postList = postRepository.getPostList().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    public PostListResponseDto getPostListByPage(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        List<PostResponseDto> postList = postRepository.getPostListByPage(pageRequest.getOffset(), pageRequest.getPageSize())
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    @Transactional
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, UserDetailsImpl userDetails) {

        Post post = new Post().builder()
                .requestDto(requestDto)
                .user(userDetails.getUser())
                .build();

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto,User user) {
        Post post = findPost(id);

        checkUser(user, post);

        post.updatePost(requestDto.getTitle());

        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = findPost(id);

        checkUser(user,post);

        postRepository.delete(post);
    }

    public Post findPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("조회하신 게시글이 없습니다")
        );
        return post;
    }

    public Boolean checkUser(User user, Post post) {
        if(!post.getUser().getId().equals(user.getId())) {
            throw new RejectedExecutionException();
        }
        return true;
    }
}
