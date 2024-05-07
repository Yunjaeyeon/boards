package com.example.boards.repository;

import com.example.boards.domain.Board;
import com.example.boards.domain.DeleteYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository <Board, Long> {
    List<Board> findByDeleteYnNot(DeleteYn deleteYn);
}
