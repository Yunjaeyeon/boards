package com.example.boards.service;

import com.example.boards.domain.DeleteYn;
import com.example.boards.dto.*;
import com.example.boards.domain.Board;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.boards.repository.BoardRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    /*
    일반적으로 "create" 작업은 데이터베이스에 새로운 엔티티를 추가하고, "update" 작업은 기존의 엔티티를 수정하는 것입니다.
    "create" 작업은 단순히 새로운 엔티티를 추가하는 것이므로, 일반적으로 트랜잭션을 사용하지 않습니다. 이는 간단한 데이터베이스 작업이기 때문에, 트랜잭션을 사용하지 않아도 데이터베이스에 안전하게 저장될 수 있기 때문입니다.
    반면에 "update" 작업은 기존의 엔티티를 수정하는 작업이기 때문에, 보다 안전한 데이터 관리를 위해 트랜잭션을 사용하는 것이 좋습니다. 트랜잭션을 사용하면 데이터베이스 작업이 원자적으로 실행되므로, 업데이트 작업 중에 오류가 발생하더라도 데이터베이스가 일관된 상태를 유지할 수 있습니다.
    따라서 "create" 작업에는 트랜잭션을 사용하지 않고, "update" 작업에는 트랜잭션을 사용하는 것이 일반적인 관례입니다.
     */

    private final BoardRepository boardRepository;

    public List<BoardDto> searchBoards() {
        List<Board> boards = boardRepository.findByDeleteYnNot(DeleteYn.Y);
        return boards.stream()
                .map(BoardDto::of)
                .collect(Collectors.toList());
    }

    public List<BoardsDto> searchBoardsDetail() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardsDto::of)
                .collect(Collectors.toList());
    }

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
                        .deleteYn(DeleteYn.N)
                        .build()
        );
        return board.getId();
    }

    @Transactional
    public MessageDto updateBoard(BoardUpdateForm boardUpdateForm) {
        Board board = boardRepository.findById(boardUpdateForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));


        // 생성일
        LocalDateTime createTime = board.getCreateTime();
        System.out.println("createTime : " +createTime);

       // 생성일로부터 경과한 날짜 계산
        long daysSinceCreation = ChronoUnit.DAYS.between(createTime, LocalDateTime.now().plusDays(1));
        System.out.println("daysSinceCreation = " + daysSinceCreation);
        // 생성일로부터 9일이 지났을 경우
        if (daysSinceCreation == 9) {
            System.out.println(" 9일" );

            board.updateBoard(
                    boardUpdateForm.getUserId(),
                    boardUpdateForm.getUserName(),
                    boardUpdateForm.getPassword(),
                    boardUpdateForm.getTitle(),
                    boardUpdateForm.getContent(),
                    boardUpdateForm.getDeleteYn(),
                    boardUpdateForm.getEmail(),
                    boardUpdateForm.getPhoneNo()
            );

            String message = "9일지났음 !";
            return ResponseEntity.ok(new MessageDto(message)).getBody();
        }

        // 생성일로부터 10일이 지났을 경우
        if (daysSinceCreation >= 10) {
            System.out.println(" 10일" );
            String message = "10일 지나면 안됨 !";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(message)).getBody();
        }

        // 게시글 업데이트
        board.updateBoard(
                boardUpdateForm.getUserId(),
                boardUpdateForm.getUserName(),
                boardUpdateForm.getPassword(),
                boardUpdateForm.getTitle(),
                boardUpdateForm.getContent(),
                boardUpdateForm.getDeleteYn(),
                boardUpdateForm.getEmail(),
                boardUpdateForm.getPhoneNo()
        );

        // 업데이트된 게시글 반환
        return new MessageDto("성공");
    }

    @Transactional
    public BoardDto updateDeleteYn(BoardUpdateForm boardUpdateForm) {
        Board board = boardRepository.findById(boardUpdateForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        board.setDeleteYn(DeleteYn.Y);
        return BoardDto.of(board);
    }

    @Transactional
    public void removeBoard(BoardRemoveForm boardRemoveForm) {
        Board board = boardRepository.findById(boardRemoveForm.getId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        boardRepository.delete(board);

    }


}