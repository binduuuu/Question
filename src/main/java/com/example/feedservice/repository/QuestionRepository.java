package com.example.feedservice.repository;

import com.example.feedservice.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Question findByQuestionId(String QuestionId);

    List<Question> findAllByTopicName(String topicName);
}
