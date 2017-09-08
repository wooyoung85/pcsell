package com.pcsell.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {	
}
