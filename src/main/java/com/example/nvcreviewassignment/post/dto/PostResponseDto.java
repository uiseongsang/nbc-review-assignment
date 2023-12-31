package com.example.nvcreviewassignment.post.dto;

import com.example.nvcreviewassignment.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getNickname();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
