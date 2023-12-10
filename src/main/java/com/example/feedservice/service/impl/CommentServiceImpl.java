package com.example.feedservice.service.impl;

import com.example.feedservice.dto.CommentDto;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.dto.ReplyDto;
import com.example.feedservice.entity.*;
import com.example.feedservice.repository.*;
import com.example.feedservice.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public Boolean addComment(CommentDto commentDto ) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        String commentId = UUID.randomUUID().toString();
        comment.setCommentId(commentId);
        Answer answer = answerRepository.findById(commentDto.getAnswerId()).get();
        comment.setQuestionId(answer.getQuestionId());
        comment.setRepliIds(new ArrayList<String>());
        Comment newComment = commentRepository.save(comment);
        List<String> commentIds = answer.getCommentIds();
        commentIds.add(commentId);
        answer.setCommentIds(commentIds);
        answerRepository.save(answer);
        return Objects.nonNull(newComment);
    }


    @Override
    public List<Comment> getAllComments () {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(String commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment;
    }

    @Override
    public void deleteCommentById(String commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Boolean addReply(ReplyDto replyDto) {
        Reply reply = new Reply();
        BeanUtils.copyProperties(replyDto, reply);
        String replyId = UUID.randomUUID().toString();
        reply.setReplyId(replyId);
        Comment comment = commentRepository.findById(replyDto.getCommentId()).get();
        List<String> replyIds = comment.getRepliIds();
        replyIds.add(replyId);
        comment.setRepliIds(replyIds);
        commentRepository.save(comment);
        replyRepository.save(reply);
        return Objects.nonNull(reply);
    }
}
