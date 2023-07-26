package com.example.nvcreviewassignment.user.repository;

import com.example.nvcreviewassignment.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsAllByNickname(String nickname);
    Optional<User> findByNickname(String nickname);
}
