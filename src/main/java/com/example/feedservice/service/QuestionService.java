package com.example.feedservice.service;

import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Question;
import com.example.feedservice.entity.Topic;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {
    Boolean topicExists(String topicName);
    String getTopicId(String topicName);
    Boolean addQuestion(QuestionDto questionDto );
    List<Question> getAllQuestions();
    Optional<Question> getQuestionById(String questionId);
    void deleteQuestionById(String questionId);
    List<Question> getAllQuestionsByCategory(String topicName);
    List<Question> getHomeQuestionsByCategory(List<String> categories);
}
