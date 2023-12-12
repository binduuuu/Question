package com.example.feedservice.service;

import com.example.feedservice.dto.AnswerDto;
import com.example.feedservice.entity.Answer;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface AnswerService {
    Boolean topicExists(String topicName);
    String getTopicId(String topicName);

    List<Answer> getHomeAnswersByCategory(int page,int size,List<String> categories);
    Boolean addAnswer (AnswerDto answerDto);
    List<Answer> getAllAnswers();
    Optional<Answer> getAnswerById(String answerId);
    void deleteAnswerById(String answerId);
    List<Answer> getAllAnswersByQuestionId(String questionId);
    int updateUpvotes(String userId, String answerId,String body);
    int updateDownvotes(String userId, String answerId,String body);
}
