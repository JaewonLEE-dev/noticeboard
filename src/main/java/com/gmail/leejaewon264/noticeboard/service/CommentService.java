package com.gmail.leejaewon264.noticeboard.service;

import com.gmail.leejaewon264.noticeboard.domain.Comment;
import com.gmail.leejaewon264.noticeboard.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 모든 댓글 조회
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // 특정 댓글 조회
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // 댓글 생성
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

