package com.example.boards.dto;

import com.example.boards.domain.BaseEntity;
import com.example.boards.domain.DeleteYn;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateForm extends BaseEntity {
    @NotNull
    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    // 비밀번호는 대소문자로 이뤄져야하고 숫자가 5개이상 특수문자가 *!@#$%을 포함을 2개이상 해야한다.
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=(?:.*[0-9]){5,})(?=(?:.*[!@#$%]){2,}).*$")
    private String password;
    @Size(max = 200)
    private String title;
    @Size(max = 1000)
    private String content;
    @Email
    private String email;
    private String phoneNo;
    private DeleteYn deleteYn;

}
