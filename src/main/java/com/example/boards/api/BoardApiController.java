package com.example.boards.api;

import com.example.boards.dto.BoardForm;
import com.example.boards.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
//해당 클래스의 final 필드나 @NonNull 어노테이션이 붙은 필드를 가지고 생성자를 생성함. 주로 의존성 주입(Dependency Injection)을 편리하게 하기 위해 사용
public class BoardApiController {

    private final BoardService boardService; // --> @RequiredArgsConstructor

    @PostMapping("/board")
    public void createBoard(@RequestBody @Valid BoardForm boardForm)  {
        System.out.println(">>>>controller !!!");
        boardService.createBoard(boardForm);
    }
}

