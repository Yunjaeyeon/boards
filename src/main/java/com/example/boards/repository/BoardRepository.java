package com.example.boards.repository;

import com.example.boards.domain.Board;
import com.example.boards.domain.DeleteYn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository <Board, Long> {
    List<Board> findByDeleteYnNot(DeleteYn deleteYn);


    Page<Board> findByDeleteYnNot(DeleteYn deleteYn, Pageable pageable);
}
