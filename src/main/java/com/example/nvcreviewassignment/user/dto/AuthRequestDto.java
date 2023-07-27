package com.example.nvcreviewassignment.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AuthRequestDto {

    @Pattern(regexp = "^[a-z0-9]{3,}$",
            message = "최소 3자 이상 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다.")
    private String nickname;

    @Pattern(regexp = "^(?!.*nickname)[a-z0-9]{4,}$",
            message = "최소 4자 이상 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다. 그리고 nickname이 들어가 있으면 안됩니다")
    private String password;

    @Pattern(regexp = "^(?!.*nickname)[a-z0-9]{4,}$",
            message = "최소 4자 이상 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다. 그리고 nickname이 들어가 있으면 안됩니다")
    private String confirmedPassword;
}
