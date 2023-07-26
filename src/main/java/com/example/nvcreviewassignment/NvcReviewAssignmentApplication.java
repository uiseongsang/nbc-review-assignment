package com.example.nvcreviewassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NvcReviewAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(NvcReviewAssignmentApplication.class, args);
    }

}
