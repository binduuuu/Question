package com.example.feedservice.controller;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.dto.AnswerDto;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Answer;
import com.example.feedservice.entity.Question;
import com.example.feedservice.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/addAnswer")
    public ApiResponse<String> addAnswer (@RequestBody AnswerDto answerDto) {
        ApiResponse<String> apiResponse;
        try {
            Boolean inserted = answerService.addAnswer(answerDto);
            if (inserted) {
                apiResponse = new ApiResponse<>("Answer added");
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add the answer");
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add the answer");
        }

        return apiResponse;
    }

    @GetMapping("/getAnswer/{answerId}")
    public ApiResponse<Answer> getAnswerById(@PathVariable("answerId") String answerId) {
        ApiResponse<Answer> apiResponse;
        try {
            Optional<Answer> answer = answerService.getAnswerById(answerId);
            if (!answer.isPresent()) {
                apiResponse = new ApiResponse<>("404", "Answer not found");
            } else {
                apiResponse = new ApiResponse<>(answer.get());
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Answer not found");
        }

        return apiResponse;

    }

    @GetMapping("/getHome")
    public ApiResponse<List<Answer>> getHomeAnswers (@RequestParam("userId") String userId,@RequestParam("page")int page,@RequestParam("size")int size) {
        ApiResponse<List<Answer>> apiResponse;
        List<String> categories = new ArrayList<>();
        categories.add("Sports");
        categories.add("Software");
        try {
            List<Answer> answers = answerService.getHomeAnswersByCategory(page,size,categories);
            apiResponse = new ApiResponse<>(answers);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Answer not found");
        }

        return apiResponse;

    }


    @DeleteMapping("/deleteAnswer/{answerId}")
    public ApiResponse<String> deleteAnswerById(@PathVariable("answerId") String answerId) {

        ApiResponse<String> apiResponse;
        try {
            Optional<Answer> answer = answerService.getAnswerById(answerId);
            if (answer.isPresent()) {
                answerService.deleteAnswerById(answerId);
                apiResponse = new ApiResponse<>("Answer deleted");
            } else
                apiResponse = new ApiResponse<>("404", "Answer not found");

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Answer not found");

        }
        return apiResponse;
    }
}
