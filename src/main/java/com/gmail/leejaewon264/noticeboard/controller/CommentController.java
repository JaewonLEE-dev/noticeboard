package com.gmail.leejaewon264.noticeboard.controller;

import com.gmail.leejaewon264.noticeboard.domain.Comment;
import com.gmail.leejaewon264.noticeboard.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 모든 댓글 조회 - 댓글 목록 페이지 렌더링
    @GetMapping
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "comment-list"; // comment-list.html로 이동
    }

    // 특정 댓글 조회 - 댓글 상세 페이지 렌더링
    @GetMapping("/{id}")
    public String getCommentById(@PathVariable Long id, Model model) {
        commentService.getCommentById(id).ifPresent(comment -> model.addAttribute("comment", comment));
        return "comment-detail"; // comment-detail.html로 이동
    }

    // 댓글 생성 폼 페이지
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "comment-form"; // comment-form.html로 이동
    }

    // 댓글 생성 처리
    @PostMapping
    public String createComment(Comment comment) {
        commentService.createComment(comment);
        return "redirect:/comments"; // 생성 후 댓글 목록 페이지로 리다이렉트
    }

    // 댓글 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments"; // 삭제 후 댓글 목록 페이지로 리다이렉트
    }
}


