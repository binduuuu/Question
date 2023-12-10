package com.example.feedservice.repository.impl;

import com.example.feedservice.entity.Answer;
import com.example.feedservice.repository.AnswerCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerCustomRepositoryImpl implements AnswerCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Answer> getHomeByCategory(int page,int size,List<String> categories) {
        Pageable pageable = PageRequest.of(page, size);

        Query query = new Query(Criteria.where("topicName").in(categories))
                .with(Sort.by(Sort.Order.desc("createdAt")))
                .with(pageable);

        return mongoTemplate.find(query, Answer.class);
    }
}
