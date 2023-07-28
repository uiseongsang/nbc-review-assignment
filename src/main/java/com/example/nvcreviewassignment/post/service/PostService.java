package com.example.nvcreviewassignment.post.service;

import com.example.nvcreviewassignment.common.security.UserDetailsImpl;
import com.example.nvcreviewassignment.post.dto.PostListResponseDto;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import com.example.nvcreviewassignment.post.dto.PostResponseDto;
import com.example.nvcreviewassignment.post.entity.Post;
import com.example.nvcreviewassignment.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostListResponseDto getPosts() {
        List<PostResponseDto> postList = postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, UserDetailsImpl userDetails) {

        Post post = new Post(requestDto, userDetails.getUser());

        postRepository.save(post);

        return new PostResponseDto(post);
    }
}
