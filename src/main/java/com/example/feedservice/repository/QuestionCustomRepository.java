package com.example.feedservice.repository;

import com.example.feedservice.entity.Question;

import java.util.List;

public interface QuestionCustomRepository {
    List<Question> getQuestionsByCategory(List<String> categories);
}
