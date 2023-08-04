package com.example.nvcreviewassignment.post.repository;

import com.example.nvcreviewassignment.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryQuery {
    //List<Post> findAllByOrderByCreatedAtDesc();
}
