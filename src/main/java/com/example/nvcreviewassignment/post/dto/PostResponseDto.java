package com.example.nvcreviewassignment.post.dto;

import com.example.nvcreviewassignment.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getNickname();
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
