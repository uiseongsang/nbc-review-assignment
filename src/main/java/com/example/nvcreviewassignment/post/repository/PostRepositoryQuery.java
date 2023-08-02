package com.example.nvcreviewassignment.post.repository;

import com.example.nvcreviewassignment.post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepositoryQuery {

    List<Post> getPostList();
    List<Post> getPostListByPage(long offset, int pageSize);
}

