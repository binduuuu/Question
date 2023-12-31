package com.example.feedservice.service.impl;

import com.example.feedservice.dto.AnswerDto;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Answer;
import com.example.feedservice.entity.EmailDetails;
import com.example.feedservice.entity.Question;
import com.example.feedservice.entity.Topic;
import com.example.feedservice.repository.*;
import com.example.feedservice.service.AnswerService;
import com.example.feedservice.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AnswerCustomRepository answerCustomRepository;

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
    public List<Answer> getHomeAnswersByCategory(int page,int size,List<String> categories) {
        return  answerCustomRepository.getHomeByCategory( page, size,categories);
    }

    @Override
    public Boolean addAnswer (AnswerDto answerDto ) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDto, answer);
        String answerId = UUID.randomUUID().toString();
        answer.setAnswerId(answerId);
        List<String> answerIds = new ArrayList<>();
        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        if(question.isPresent()){
//            Question question1 = question.get();
            answerIds = question.get().getAnswerIds();
            answerIds.add(answerId);
            question.get().setAnswerIds(answerIds);
            questionRepository.save(question.get());
            answer.setTopicName(question.get().getTopicName());
        }
        else{
            answerIds.add(answerId);
        }
        answer.setCommentVisibility(true);
        answer.setDownvoteIds(new ArrayList<String>());
        answer.setUpvoteIds(new ArrayList<String>());
        answer.setCommentIds(new ArrayList<String>());
        Answer newAnswer = answerRepository.save(answer);
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

    @Override
    public List<Answer> getAllAnswersByQuestionId(String questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Override
    public int updateUpvotes(String userId, String answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        List<String> upvotedIds = answer.getUpvoteIds();
        upvotedIds.add(userId);
        answer.setUpvotes(upvotedIds.size());
        answer.setUpvoteIds(upvotedIds);
        answerRepository.save(answer);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setBody("some just DownVoted your Answer");
        emailDetails.setRecipient("sanskaragarwaljmt@gmail.com");
        emailDetails.setSource("Quora");
        emailDetails.setSubject("upvoted");
        emailService.sendEmail(emailDetails);
        return upvotedIds.size();
    }

    @Override
    public int updateDownvotes(String userId, String answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        List<String> downvoteIds = answer.getDownvoteIds();
        downvoteIds.add(userId);
        answer.setDownvotes(downvoteIds.size());
        answer.setDownvoteIds(downvoteIds);
        answerRepository.save(answer);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setBody("some just DownVoted your Answer");
        emailDetails.setRecipient("sanskaragarwaljmt@gmail.com");
        emailDetails.setSource("Quora");
        emailDetails.setSubject("Downvoted");
        emailService.sendEmail(emailDetails);
        return downvoteIds.size();
    }

}
