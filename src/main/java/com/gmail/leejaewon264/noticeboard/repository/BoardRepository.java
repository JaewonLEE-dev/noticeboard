package com.gmail.leejaewon264.noticeboard.repository;

import com.gmail.leejaewon264.noticeboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
