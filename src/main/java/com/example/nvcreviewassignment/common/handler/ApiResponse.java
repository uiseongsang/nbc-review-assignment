package com.example.nvcreviewassignment.common.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String msg;
    private Integer statusCode;

    public ApiResponse(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

