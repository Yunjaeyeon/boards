package com.example.boards;

import com.example.boards.domain.Board;
import com.example.boards.domain.DeleteYn;
import com.example.boards.dto.*;
import com.example.boards.repository.BoardRepository;
import com.example.boards.service.BoardService;
import org.apache.coyote.BadRequestException;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.management.Query.eq;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BoardsApplicationTests {

    @Test
    void contextLoads() {
    }
    @AfterEach //  단위 테스트 후 데이터 삭제
    public void cleanup(){
        boardRepository.deleteAll();
    }

    @Mock //test에서 Repository 사용할 때
    private BoardRepository boardRepository;
    @InjectMocks //test에서 Service 사용할 때
    private BoardService boardService;




    @Test
    @DisplayName("searchBoards 테스트")
    void testSearchBoards() {

        // given

        // when
        List<BoardDto> boardDtos = boardService.searchBoards();

        // then:
        assertNotNull(boardDtos);

    }

    @Test
    @DisplayName("searchPagedBoards 테스트")
    public void testSearchPagedBoards() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        List<Board> fakeBoards = new ArrayList<>();
        Page<Board> fakePage = new PageImpl<>(fakeBoards);

        //when
        when(boardRepository.findByDeleteYnNot(any(), any())).thenReturn(fakePage);

        // then
        assertNotNull(fakeBoards);
    }

    @Test
    @DisplayName("searchBoardDetail 테스트")
    void testSearchBoardsDetail() {
        // given

        // when
        List<BoardsDto> boardDtos = boardService.searchBoardsDetail();

        // then:
        assertNotNull(boardDtos);
    }

    @Test
    @DisplayName("createBoard 테스트")
    public void testCreateBoard() {
        // Given
        BoardForm boardForm = new BoardForm();
        boardForm.setUserId("userId1");
        boardForm.setUserName("userName1");
        boardForm.setPassword("Password123!!!");
        boardForm.setTitle("title1");
        boardForm.setContent("content1");
        boardForm.setEmail("email1@example.com");
        boardForm.setPhoneNo("01011111111");


        boardRepository.save(
                Board.builder()
                        .userId(boardForm.getUserId())
                        .userName(boardForm.getUserName())
                        .password(boardForm.getPassword())
                        .title(boardForm.getTitle())
                        .content(boardForm.getContent())
                        .email(boardForm.getEmail())
                        .phoneNo(boardForm.getPhoneNo())
                        .deleteYn(DeleteYn.N)
                        .build());

        //when
        List<Board> board = boardRepository.findAll();

        //then
        assertNotNull(board);
    }

    @Test
    @DisplayName("updateBoard테스트")

    public void updateBoardTest(){
        // Given
        BoardUpdateForm boardUpdateForm = new BoardUpdateForm();
        boardUpdateForm.setId(1L);
        boardUpdateForm.setUserId("userId1");
        boardUpdateForm.setUserName("userName1");
        boardUpdateForm.setPassword("Password123!!!");
        boardUpdateForm.setTitle("title1");
        boardUpdateForm.setContent("content1");
        boardUpdateForm.setEmail("email1@example.com");
        boardUpdateForm.setPhoneNo("01011111111");

        Board board = new Board();

        // when
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // Then
        assertNotNull(board);
    }

    @Test
    @DisplayName("removeBoard 테스트")
    void removeBoardTest() {
        // Given
        Long boardId = 1L;

        // When
        boardRepository.deleteById(boardId);

        // Then

        assertFalse(boardRepository.existsById(boardId));
    }

}


