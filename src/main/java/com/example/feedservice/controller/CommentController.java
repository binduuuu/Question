package com.example.feedservice.controller;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.dto.CommentDto;
import com.example.feedservice.dto.ReplyDto;
import com.example.feedservice.entity.Comment;
import com.example.feedservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ApiResponse<String> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse<String> apiResponse;
        try {
            Boolean inserted = commentService.addComment(commentDto);
            if (inserted) {
                apiResponse = new ApiResponse<>("Comment added");
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add the comment");
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add the comment");
        }

        return apiResponse;
    }

    @GetMapping("/getComment/{commentId}")
    public ApiResponse<Comment> getComment (@PathVariable("commentId") String commentId) {
        ApiResponse<Comment> apiResponse;
        try {
            Optional<Comment> comment = commentService.getCommentById(commentId);
            if (!comment.isPresent()) {
                apiResponse = new ApiResponse<>("404", "Comment not found");
            } else {
                apiResponse = new ApiResponse<>(comment.get());
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Comment not found");
        }

        return apiResponse;

    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ApiResponse<String> deleteCommentById(@PathVariable("commentId") String commentId) {

        ApiResponse<String> apiResponse;
        try {
            Optional<Comment> comment = commentService.getCommentById(commentId);
            if (comment.isPresent()) {
                commentService.deleteCommentById(commentId);
                apiResponse = new ApiResponse<>("Comment deleted");
            } else
                apiResponse = new ApiResponse<>("404", "Comment not found");

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Comment not found");
        }
        return apiResponse;
    }

    @PostMapping("/addReply")
    public ApiResponse<String> addReply(@RequestBody ReplyDto replyDto) {
        ApiResponse<String> apiResponse;
        Boolean inserted = commentService.addReply(replyDto);
        if (inserted) {
            apiResponse = new ApiResponse<>("Reply added");
        } else {
            apiResponse = new ApiResponse<>("404", "Could not add the reply");
        }

        return apiResponse;
    }
}
