package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "answer")
public class Answer {


    @Id
    private String answerId;
    private String questionId;
    private String userId;
    private String answer;
    private Date createdAt;
    private int upvotes;
    private int downvotes;
    private List<String> commentIds;
    private List<String> upvoteIds;
    private List<String> downvoteIds;
    private String topicName;
    private Boolean commentVisibility;
}
