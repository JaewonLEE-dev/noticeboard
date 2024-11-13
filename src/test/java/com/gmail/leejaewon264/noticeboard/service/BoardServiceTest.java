package com.gmail.leejaewon264.noticeboard.service;

import com.gmail.leejaewon264.noticeboard.domain.Board;
import com.gmail.leejaewon264.noticeboard.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBoardTest() {
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setWriter("Test Writer");

        when(boardRepository.save(board)).thenReturn(board);

        Board createdBoard = boardService.createBoard(board);
        assertEquals("Test Title", createdBoard.getTitle());
        verify(boardRepository, times(1)).save(board);
    }

    @Test
    void getBoardByIdTest() {
        Board board = new Board();
        board.setId(1L);
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        Optional<Board> foundBoard = boardService.getBoardById(1L);
        assertTrue(foundBoard.isPresent());
        assertEquals(1L, foundBoard.get().getId());
    }

    @Test
    void deleteBoardTest() {
        Long boardId = 1L;
        doNothing().when(boardRepository).deleteById(boardId);

        boardService.deleteBoard(boardId);
        verify(boardRepository, times(1)).deleteById(boardId);
    }
}
