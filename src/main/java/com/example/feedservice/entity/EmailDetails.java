package com.example.feedservice.entity;

import lombok.Data;

@Data
public class EmailDetails {
    private String recipient;
    private String subject;
    private String body;
    private String source;
}
