package com.example.boards.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    //@NotNull - null x
    //@NotEmpty - null,("") x
    //@NotBlank - (""), (" "), null x
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Size(max = 200)
    private String title;
    @Size(max = 1000)
    private String content;
    private String email;
    private String phoneNo;
}
