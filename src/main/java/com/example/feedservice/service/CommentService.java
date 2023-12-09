package com.example.feedservice.service;

import com.example.feedservice.dto.CommentDto;
import com.example.feedservice.dto.ReplyDto;
import com.example.feedservice.entity.Comment;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {

    Boolean addComment(CommentDto commentDto );
    List<Comment> getAllComments ();
    Optional<Comment> getCommentById(String commentId);
    void deleteCommentById(String commentId);
    Boolean addReply(ReplyDto replyDto);
}
