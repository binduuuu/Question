package com.example.feedservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "topic")
public class Topic {
    @Id
    private String topicId;
    private String topicName;
}
