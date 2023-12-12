package com.example.feedservice.controller;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Question;
import com.example.feedservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quora/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public ApiResponse<String> addQuestion(@RequestBody QuestionDto questionDto) {
        ApiResponse<String> apiResponse;
        try {
            Boolean inserted = questionService.addQuestion(questionDto);
            if (inserted) {
                apiResponse = new ApiResponse<>("Question added");
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add the question");
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add the question");
        }

        return apiResponse;
    }

    @GetMapping("/getQuestion/{questionId}")
    public ApiResponse<Question> getQuestion (@PathVariable("questionId") String questionId) {
        ApiResponse<Question> apiResponse;
        try {
            Optional<Question> question = questionService.getQuestionById(questionId);
            if (!question.isPresent()) {
                apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
            } else {
                apiResponse = new ApiResponse<>(question.get());
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
        }

        return apiResponse;

    }


    @DeleteMapping("/deleteQuestion/{questionId}")
    public ApiResponse<String> deleteQuestionById(@PathVariable("questionId") String questionId) {

        ApiResponse<String> apiResponse;
        try {
            Optional<Question> question = questionService.getQuestionById(questionId);
            if (question.isPresent()) {
                questionService.deleteQuestionById(questionId);
                apiResponse = new ApiResponse<>("Question deleted");
            } else
                apiResponse = new ApiResponse<>("404", "Check the question id and try again, question not found");

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the question id and try again, question not found");

        }
        return apiResponse;
    }

    @GetMapping("/getQuestionsByCategory/{topicName}")
    public ApiResponse<List<Question>> getAllQuestionsByCategory(@PathVariable("topicName") String topicName) {
        ApiResponse<List<Question>> apiResponse;
        try {
            List<Question> questions = questionService.getAllQuestionsByCategory(topicName);
            apiResponse = new ApiResponse<>(questions);
        }
        catch(Exception e) {
            apiResponse = new ApiResponse<>("404", "No questions found");
        }
        return apiResponse;
    }

    @GetMapping("/getQuestionsByCategory")
    public ApiResponse<List<Question>> getQuestionsByCategory (@RequestParam("userId") String userId) {
        ApiResponse<List<Question>> apiResponse;
        List<String> categories = new ArrayList<>();
        categories.add("Sports");
        categories.add("Software");
        try {
            List<Question> questions = questionService.getHomeQuestionsByCategory(categories);
            apiResponse = new ApiResponse<>(questions);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Questions not found");
        }

        return apiResponse;
    }
}
