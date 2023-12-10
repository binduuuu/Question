package com.example.feedservice.repository;

import com.example.feedservice.entity.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnswerCustomRepository  {
    List<Answer> getHomeByCategory(int page,int size,List<String> categories);
}
