package com.gmail.leejaewon264.noticeboard.controller;

import com.gmail.leejaewon264.noticeboard.domain.Board;
import com.gmail.leejaewon264.noticeboard.domain.Comment;
import com.gmail.leejaewon264.noticeboard.service.BoardService;
import com.gmail.leejaewon264.noticeboard.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    // 루트 경로로 접속 시 게시글 목록 페이지로 이동
    @GetMapping("/")
    public String home(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board-list"; // board-list.html 렌더링
    }

    // 모든 게시글 조회 - 리스트 페이지 렌더링
    @GetMapping("/boards")
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board-list"; // board-list.html로 이동
    }

    // 특정 게시글 및 댓글 조회 - 게시글 상세 페이지 렌더링
    @GetMapping("/boards/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        boardService.getBoardById(id).ifPresent(board -> model.addAttribute("board", board));

        // 게시글 ID에 해당하는 댓글 목록 조회
        List<Comment> comments = commentService.getCommentsByBoardId(id);
        model.addAttribute("comments", comments);

        return "board-detail"; // board-detail.html로 이동
    }

    // 게시글 생성 폼 페이지
    @GetMapping("/boards/new")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new Board());
        return "board-form"; // board-form.html로 이동
    }

    // 게시글 생성 처리
    @PostMapping("/boards")
    public String createBoard(Board board) {
        boardService.createBoard(board);
        return "redirect:/boards"; // 생성 후 목록 페이지로 리다이렉트
    }

    // 게시글 수정 폼 페이지
    @GetMapping("/boards/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        boardService.getBoardById(id).ifPresent(board -> model.addAttribute("board", board));
        return "board-form"; // board-form.html로 이동
    }

    // 게시글 수정 처리
    @PostMapping("/boards/{id}/edit")
    public String updateBoard(@PathVariable Long id, Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/boards"; // 수정 후 목록 페이지로 리다이렉트
    }

    // 게시글 삭제 처리
    @PostMapping("/boards/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards"; // 삭제 후 목록 페이지로 리다이렉트
    }
}







