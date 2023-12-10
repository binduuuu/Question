package com.example.feedservice.repository;

import com.example.feedservice.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {

    Boolean existsByTopicName(String topicName);
    Topic findTopicIdByTopicName(String topicName);
}
