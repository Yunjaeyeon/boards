package com.example.boards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    private String userId;
    private String userName;
    private String password;
    private String title;
    private String content;
    private String email;
    private String phoneNo;
}
