package com.example.feedservice.repository;

import com.example.feedservice.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Question findByQuestionId(String QuestionId);
}
