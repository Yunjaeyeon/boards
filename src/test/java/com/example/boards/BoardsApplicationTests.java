package com.example.boards;

import com.example.boards.domain.Board;
import com.example.boards.domain.DeleteYn;
import com.example.boards.dto.BoardDto;
import com.example.boards.repository.BoardRepository;
import com.example.boards.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

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
    @Mock //test에서 Repository 사용할 때
    private BoardRepository boardRepository;
    @InjectMocks //test에서 Service 사용할 때
    private BoardService boardService;

    @Test
    void testSearchBoards() {
        // given

        // when
        List<BoardDto> boardDtos = boardService.searchBoards();

        // then:
        assertNotNull(boardDtos); // 반환된 리스트가 null이 아닌지 확인
    }

    @Test
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


}
