package com.example.boards.service;

import com.example.boards.domain.DeleteYn;
import com.example.boards.dto.*;
import com.example.boards.domain.Board;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.boards.repository.BoardRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> searchBoards(Long id) {
        List<Board> boards = boardRepository.findByIdAndIsDeletedFalse(id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));
        return boards.stream()
                .map(BoardDto::of)
                .collect(Collectors.toList());
    }

    public Page<BoardDto> searchPagedBoards(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findByIsDeletedFalse(pageable);
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boardPage.getContent()) { // boardPage.getContent()은 Page 객체에서 현재 페이지에 포함된 항목들을 가져오는 메서드
            boardDtos.add(BoardDto.of(board));
        }
        return new PageImpl<>(boardDtos, pageable, boardPage.getTotalElements());
    }


    public List<BoardsDto> searchBoardsDetail() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardsDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createBoard(BoardForm boardForm) {
        Board board = boardRepository.save(
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
        return board.getId();
    }

    @Transactional
    public MessageDto updateBoard(BoardUpdateForm boardUpdateForm) {
        Board board = boardRepository.findById(boardUpdateForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        LocalDateTime createTime = board.getCreateTime();

        long daysSinceCreation = ChronoUnit.DAYS.between(createTime, LocalDateTime.now().plusDays(1));

        try {
            if (daysSinceCreation == 9) {
                board.updateBoard(
                        boardUpdateForm.getUserId(),
                        boardUpdateForm.getUserName(),
                        boardUpdateForm.getPassword(),
                        boardUpdateForm.getTitle(),
                        boardUpdateForm.getContent(),
                        boardUpdateForm.getEmail(),
                        boardUpdateForm.getPhoneNo()
                );
                String message = "9일이 지났습니다!";
                return new MessageDto(message);
            }

            if (daysSinceCreation >= 10) {
                String message = "10일 지나면 안됩니다!";
                throw new IllegalStateException(message);
            }

            board.updateBoard(
                    boardUpdateForm.getUserId(),
                    boardUpdateForm.getUserName(),
                    boardUpdateForm.getPassword(),
                    boardUpdateForm.getTitle(),
                    boardUpdateForm.getContent(),
                    boardUpdateForm.getEmail(),
                    boardUpdateForm.getPhoneNo()
            );

            return new MessageDto("성공 !");
        } catch (IllegalStateException e) {
            return new MessageDto(e.getMessage());
        }
    }


    @Transactional
    public BoardDto updateDeleteYn(BoardUpdateForm boardUpdateForm) {
        Board board = boardRepository.findById(boardUpdateForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

       board.setIsDeleted();
        return BoardDto.of(board);
    }


    @Transactional
    public void removeBoard(BoardRemoveForm boardRemoveForm) {
        Board board = boardRepository.findById(boardRemoveForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));
        boardRepository.delete(board);
    }
}
