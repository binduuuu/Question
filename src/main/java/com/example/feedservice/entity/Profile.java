package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Profile {

    @Id
    private String profileId;
    private String profileName;
    private String profileType;
    private String profileAvatar;
    private String profileStatus;
    private String rankingId;
    private int points;
    private String profileEmail;

    private String profileClassification;
}
