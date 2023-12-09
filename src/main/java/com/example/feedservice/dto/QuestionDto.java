package com.example.feedservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDto {
    private String question;
    private String userId;
    private Date createdAt;
//    private List<Integer> answerIds;
    private String status;
//    private String topicId;
    private String topicName;
}
