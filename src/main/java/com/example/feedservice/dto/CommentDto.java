package com.example.feedservice.dto;

import com.example.feedservice.entity.Comment;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CommentDto {

    private String userId;
    private String content;
//    private List<Integer> commentIds;
//    private List<Comment> comments;
    private String commentTypes;
//    private String questionId;
    private String answerId;
    private List<String> replies;
}
