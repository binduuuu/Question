package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "comment")
public class Comment {

    @Id
    private String commentId;
    private String userId;
    private String content;
    private List<String> replies;
    private String commentTypes;
    private String questionId;
    private String answerId;
}
