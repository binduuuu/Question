package com.example.feedservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AnswerResponseDto {

    private String question;
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
