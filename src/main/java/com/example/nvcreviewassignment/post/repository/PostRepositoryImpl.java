package com.example.nvcreviewassignment.post.repository;

import com.example.nvcreviewassignment.post.entity.Post;
import com.example.nvcreviewassignment.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryQuery{

    public PostRepositoryImpl() {
        super(Post.class);
    }

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Post> getPostList() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QPost qPost = QPost.post;

        List<Post> res = queryFactory
                .selectFrom(qPost)
                .orderBy(qPost.createdAt.desc())
                .fetch();

        return res;
    }
}
