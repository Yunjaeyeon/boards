package com.example.boards.service;

import com.example.boards.dto.BoardForm;
import com.example.boards.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.boards.repository.BoardRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardForm boardForm) {
        boardRepository.save(
                Board.builder()
                        .userId(boardForm.getUserId())
                        .userName(boardForm.getUserName())
                        .password(boardForm.getPassword())
                        .title(boardForm.getTitle())
                        .content(boardForm.getContent())
                        .build()
        );
    }
}