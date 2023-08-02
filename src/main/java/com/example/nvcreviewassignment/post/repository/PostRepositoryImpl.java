package com.example.nvcreviewassignment.post.repository;

import com.example.nvcreviewassignment.post.entity.Post;
import com.example.nvcreviewassignment.post.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryQuery{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getPostList() {
        QPost qPost = QPost.post;

        List<Post> res = queryFactory
                .selectFrom(qPost)
                .orderBy(qPost.createdAt.desc())
                .fetch();

        return res;
    }

    @Override
    public List<Post> getPostListByPage(long offset, int pageSize) {
        QPost qPost = QPost.post;

        return queryFactory
                .selectFrom(qPost)
                .offset(offset) // 조회하려고 하는 페이지가 과거일수록 느려짐. -> 최신 페이지에 빠름(많이 사용)
                .limit(pageSize)
                .fetch();
    }
    // DB에서 offset이란? -> 하나의 레코드의 varchar(10) - 10 byte, int - 4byte, Timestamped 값을 넣어줄 때 자료형의 순서대로
    // byte가 들어가는데... 위에 자료형들을 예를 들자면 가장 먼저 varchar(10)에서 10 byte를 할당한후 int인 4 byte를 저장을 한다
    // 그래서 레코드 시작 1번 주소인 varchar(10)하고 그 다음 주소인 int의 차이를 offset이라고 한다.
    // -> 사용자는 이걸 모르기 때문에 자체적으로 offset이라는걸 지원을 해줘서 우리가 pageRequest를 사용할 수 있는것이다.
}
