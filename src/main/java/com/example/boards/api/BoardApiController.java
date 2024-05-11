package com.example.boards.api;

import com.example.boards.dto.*;
import com.example.boards.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    // 모든 게시글 조회
    @GetMapping("/boards/{id}")
    public List<BoardDto> searchBoards(@PathVariable Long id) {
        return boardService.searchBoards(id);
    }

    // 페이징 처리된 모든 게시글 조회
    @GetMapping("/pagedBoards")
    public Page<BoardDto> searchPagedBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return boardService.searchPagedBoards(pageable);
    }

    // 상세 게시글 조회
    @GetMapping("/boardsDetail")
    public List<BoardsDto> searchBoardsDetail() {
        return boardService.searchBoardsDetail();
    }

    // 게시글 생성
    @PostMapping("/board")
    public Long createBoard(@RequestBody @Valid BoardForm boardForm) {
        return boardService.createBoard(boardForm);
    }

    // 게시글 업데이트
    @PutMapping("/board")
    public MessageDto updateBoard(@RequestBody @Valid BoardUpdateForm boardUpdateForm){
        return boardService.updateBoard(boardUpdateForm);
    }

    // 게시글 삭제 여부 업데이트
    @PutMapping("/boardDeleteYn")
    public BoardDto updateDeleteYn(@RequestBody @Valid BoardUpdateForm boardUpdateForm) {
        return boardService.updateDeleteYn(boardUpdateForm);
    }

    // 게시글 삭제
    @DeleteMapping("/board")
    public void removeBoard(@RequestBody BoardRemoveForm boardRemoveForm) {
        boardService.removeBoard(boardRemoveForm);
    }
}
