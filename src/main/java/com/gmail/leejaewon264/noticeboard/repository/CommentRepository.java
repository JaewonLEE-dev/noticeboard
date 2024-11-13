package com.gmail.leejaewon264.noticeboard.repository;

import com.gmail.leejaewon264.noticeboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글 ID에 대한 댓글 조회
    List<Comment> findByBoardId(Long boardId);
}

