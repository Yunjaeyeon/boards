package com.example.boards.dto;

import com.example.boards.domain.Board;
import com.example.boards.domain.DeleteYn;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {
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


    // 생성자
//    public BoardDto(String userId, String userName, String password, String title, String content, String email, String phoneNo) {
//        this.userId = userId;
//        this.userName = userName;
//        this.password = password;
//        this.title = title;
//        this.content = content;
//        this.email = email;
//        this.phoneNo = phoneNo;
//    }

    // of 메서드
    public static BoardDto of(Board board) {
        return new BoardDto(
                board.getUserId(),
                board.getUserName(),
                board.getPassword(),
                board.getTitle(),
                board.getContent(),
                board.getEmail(),
                board.getPhoneNo(),
                board.getDeleteYn()
        );
    }
}
