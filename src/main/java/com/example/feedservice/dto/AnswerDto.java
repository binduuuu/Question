package com.example.feedservice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class AnswerDto {

    private String questionId;
    private String userId;
    private Date createdAt;
    //answerIds
    private int upvotes;
    private int downvotes;
//    private String commentIds;
//    private String topicName;
}

