package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "answer")
public class Answer {

    private String questionId;

    @Id
    private String answerId;
    private String userId;
    private Date createdAt;
    //answerIds
    private int upvotes;
    private int downvotes;
    private List<String> commentIds;
    private String topicId;
    private String topicName;
    private String commentVisibility;
}
