package com.example.feedservice.service.impl;

import com.example.feedservice.dto.AnswerDto;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Answer;
import com.example.feedservice.entity.Question;
import com.example.feedservice.entity.Topic;
import com.example.feedservice.repository.AnswerRepository;
import com.example.feedservice.repository.CommentRepository;
import com.example.feedservice.repository.QuestionRepository;
import com.example.feedservice.repository.TopicRepository;
import com.example.feedservice.service.AnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Boolean topicExists(String topicName) {
        return topicRepository.existsByTopicName(topicName);
    }

    @Override
    public String getTopicId(String topicName) {
        Topic topic = topicRepository.findTopicIdByTopicName(topicName);
        return topic.getTopicId();
    }

    @Override
    public Boolean addAnswer (AnswerDto answerDto ) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDto, answer);
        String answerId = UUID.randomUUID().toString();
        answer.setAnswerId(answerId);
//        String topicName = answerDto.getTopicName();
//        answer.setTopicId(getTopicId(topicName));

        Question question = questionRepository.getQuestionByQuestionId(answerDto.getQuestionId());
        List<String> answerIds = question.getAnswerIds();
//        answerIds.add(answerId);
        answer.setTopicId(question.getTopicId());
        answer.setTopicName(question.getTopicName());
        answerIds.add(answerId);
        question.setAnswerIds(answerIds);
        questionRepository.save(question);
        Answer newAnswer = answerRepository.save(answer);

//        answerIds.add(answerId);
//        question.setAnswerIds(answerIds);
//        questionRepository.save(question);
        return Objects.nonNull(newAnswer);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> getAnswerById(String answerId){
        Optional<Answer> answer = answerRepository.findById(answerId);
        return answer;
    }

    @Override
    public void deleteAnswerById(String answerId) {
        answerRepository.deleteById(answerId);
    }

}
