package com.example.nvcreviewassignment.post.entity;

import com.example.nvcreviewassignment.common.entity.Timestaped;
import com.example.nvcreviewassignment.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestaped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String nickname;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
    }
}
