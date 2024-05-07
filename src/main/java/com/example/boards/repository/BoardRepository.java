package com.example.boards.repository;

import com.example.boards.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository <Board, Long> {
}
