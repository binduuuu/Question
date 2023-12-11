package com.example.feedservice.service.impl;

import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Question;
import com.example.feedservice.entity.Topic;
import com.example.feedservice.repository.*;
import com.example.feedservice.service.QuestionService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private QuestionCustomRepository questionCustomRepository;

    public Boolean topicExists(String topicName) {
        return topicRepository.existsByTopicName(topicName);
    }

    public String getTopicId(String topicName) {
        Topic topic = topicRepository.findTopicIdByTopicName(topicName);
        return topic.getTopicId();
    }

    @Override
    public Boolean addQuestion(QuestionDto questionDto ) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        String questionId = UUID.randomUUID().toString();
        question.setQuestionId(questionId);
        List<String> answerIds = new ArrayList<>();
        question.setAnswerIds(answerIds);
        String topicName = questionDto.getTopicName();

//        if(!topicExists(topicName)) {
//            Topic newTopic = new Topic();
//            String topicId = UUID.randomUUID().toString();
//            newTopic.setTopicId(topicId);
//            newTopic.setTopicName(questionDto.getTopicName());
//            topicRepository.save(newTopic);
//        }
//        else {
//            String topicId = getTopicId(questionDto.getTopicName());
//            if(topicId!=null) {
//                question.setTopicId(topicId);
//            }
//        }
        Question newQuestion = questionRepository.save(question);
        return Objects.nonNull(newQuestion);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(String questionId){
        Optional<Question> question = questionRepository.findById(questionId);
        return question;
    }

    public void deleteQuestionById(String questionId) {
        questionRepository.deleteById(questionId);
    }

    public List<Question> getAllQuestionsByCategory(String topicName) {
        return questionRepository.findAllByTopicName(topicName);
    }

    @Override
    public List<Question> getHomeQuestionsByCategory(List<String> categories) {
        return  questionCustomRepository.getQuestionsByCategory(categories);
    }

}
