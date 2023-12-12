package com.example.feedservice.dto;

import lombok.Data;

@Data
public class SearchDto {
    private  String profileId;
    private String searchTerm;
    private int points;
    private String avatar;
    private  String businessProfileId;
    private int followersCount;
    private String questionId;
    private int answerCount;
}
