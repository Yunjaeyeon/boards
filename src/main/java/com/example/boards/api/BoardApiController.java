package com.example.boards.api;

import com.example.boards.dto.*;
import com.example.boards.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public List<BoardDto> searchBoards() {
        List<BoardDto> boardDtos = boardService.searchBoards();
        return boardDtos;
    }

    @GetMapping("/boardsDetail")
    public List<BoardsDto> searchBoardsDetail() {
        List<BoardsDto> boardsDtos = boardService.searchBoardsDetail();
        return boardsDtos;
    }

    @PostMapping("/board")
    public Long createBoard(@RequestBody @Valid BoardForm boardForm)  {
        System.out.println(">>>>controller !!!");
        return boardService.createBoard(boardForm);
    }

    @PutMapping("/board")
    public BoardDto updateBoard(@RequestBody @Valid BoardUpdateForm boardUpdateForm)  {
        System.out.println(">>>>controller 222!!!");
        return boardService.updateBoard(boardUpdateForm);
    }

    @PutMapping("/boardDeleteYn")
    public BoardDto updateDeleteYn(@RequestBody @Valid BoardUpdateForm boardUpdateForm)  {
        System.out.println(">>>>controller 333!!!");
        return boardService.updateBoard(boardUpdateForm);
    }

    @DeleteMapping("/board")
    public void removeBoard(@RequestBody BoardRemoveForm boardRemoveForm)  {
        System.out.println(">>>>controller 444!!!");
        boardService.removeBoard(boardRemoveForm);
    }
}
