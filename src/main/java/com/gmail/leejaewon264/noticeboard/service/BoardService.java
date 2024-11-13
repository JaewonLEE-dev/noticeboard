package com.gmail.leejaewon264.noticeboard.service;

import com.gmail.leejaewon264.noticeboard.domain.Board;
import com.gmail.leejaewon264.noticeboard.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 모든 게시글 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 특정 게시글 조회
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    // 게시글 생성
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 게시글 수정
    public Board updateBoard(Long id, Board updatedBoard) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(updatedBoard.getTitle());
                    board.setContent(updatedBoard.getContent());
                    return boardRepository.save(board);
                })
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id));
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}

