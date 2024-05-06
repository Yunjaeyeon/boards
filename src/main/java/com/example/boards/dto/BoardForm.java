package com.example.boards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    //@NotNull - null 허용 x, @NotBlank - string 타입 빈 값 허용 x, @NotEmpty - null, 빈 문자열 허용x
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private String title;
    private String content;
    private String email;
    private String phoneNo;
}
