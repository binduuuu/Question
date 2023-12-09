package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "question")
public class Question {

    @Id
    private String questionId;
    private String question;
    private String userId;
    private Date createdAt;
    private List<String> answerIds;
    private String status;
    private String topicId;
    private String topicName;
}