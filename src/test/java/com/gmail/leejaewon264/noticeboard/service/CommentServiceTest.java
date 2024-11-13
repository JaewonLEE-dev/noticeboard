package com.gmail.leejaewon264.noticeboard.service;

import com.gmail.leejaewon264.noticeboard.domain.Comment;
import com.gmail.leejaewon264.noticeboard.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCommentTest() {
        Comment comment = new Comment();
        comment.setContent("Test Comment");
        comment.setWriter("Test Writer");

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment createdComment = commentService.createComment(comment);
        assertEquals("Test Comment", createdComment.getContent());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void getCommentByIdTest() {
        Comment comment = new Comment();
        comment.setId(1L);
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        Optional<Comment> foundComment = commentService.getCommentById(1L);
        assertTrue(foundComment.isPresent());
        assertEquals(1L, foundComment.get().getId());
    }

    @Test
    void deleteCommentTest() {
        Long commentId = 1L;
        doNothing().when(commentRepository).deleteById(commentId);

        commentService.deleteComment(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
