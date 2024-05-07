package com.example.boards.service;

import com.example.boards.dto.BoardDto;
import com.example.boards.dto.BoardForm;
import com.example.boards.domain.Board;
import com.example.boards.dto.BoardUpdateForm;
import jakarta.persistence.EntityNotFoundException;
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
                        .email(boardForm.getEmail())
                        .phoneNo(boardForm.getPhoneNo())
                        .build()
        );
    }

    @Transactional
    public BoardDto updateBoard(BoardUpdateForm boardUpdateForm) {
        Board board = boardRepository.findById(boardUpdateForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        board.updateBoard (
                boardUpdateForm.getUserId(),
                boardUpdateForm.getUserName(),
                boardUpdateForm.getPassword(),
                boardUpdateForm.getTitle(),
                boardUpdateForm.getContent(),
                boardUpdateForm.getEmail(),
                boardUpdateForm.getPhoneNo()
        );
        return BoardDto.of(board);
    }

}