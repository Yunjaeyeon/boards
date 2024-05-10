package com.example.boards.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String title;
    private String content;
    private String email;
    private String phoneNo;
   // @Enumerated(EnumType.STRING)
    //private DeleteYn deleteYn;


    @Builder
    public Board(String userId, String userName, String password, String title, String content, String email, String phoneNo) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.email = email;
        this.phoneNo = phoneNo;
        //this.isDeleted = isDeleted();
    }

    public void updateBoard(
            String userId,
            String userName,
            String password,
            String title,
            String content,
            String email,
            String phoneNo

    ) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.email = email;
        this.phoneNo = phoneNo;

    }

    public void setIsDeleted() {
        this.isDeleted = true;
    }


}
