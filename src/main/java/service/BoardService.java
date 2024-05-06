package service;

import domain.Board;
import dto.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BoardRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

    public void createBoard(BoardForm boardForm) {
        boardRepository.save(
                Board.builder()
                        .userName(boardForm.getUserName())
                        .password(boardForm.getPassword())
                        .title(boardForm.getTitle())
                        .content(boardForm.getContent())
                        .build()
        );
    }
}