package com.example.feedservice.repository;

import com.example.feedservice.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {
    Answer getAnswerByAnswerId(String answerId);
    List<Answer> findAllByQuestionId(String questionId);
}
