package com.example.feedservice.repository.impl;

import com.example.feedservice.entity.Question;
import com.example.feedservice.repository.QuestionCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Question> getQuestionsByCategory(List<String> categories) {

         Query query = new Query(Criteria.where("topicName").in(categories))
                .with(Sort.by(Sort.Order.desc("createdAt")));

        return mongoTemplate.find(query, Question.class);
    }
}
