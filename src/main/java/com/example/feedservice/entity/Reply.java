package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reply")
public class Reply {
    private String replyId;
    private String commentId;
    private String message;
    private String userId;
}
