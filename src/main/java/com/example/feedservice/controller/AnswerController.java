package com.example.feedservice.controller;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.FeignHandler.ProfileFeign;
import com.example.feedservice.dto.AnswerDto;
import com.example.feedservice.dto.QuestionDto;
import com.example.feedservice.entity.Answer;
import com.example.feedservice.entity.Profile;
import com.example.feedservice.entity.Question;
import com.example.feedservice.repository.AnswerRepository;
import com.example.feedservice.repository.QuestionRepository;
import com.example.feedservice.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quora/answer")
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ProfileFeign profileFeign;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

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

    @GetMapping("/getAnswers/{questionId}")
    public ApiResponse<List<Answer>> getAllAnswersByQuestionId(@PathVariable("questionId") String questionId) {
        ApiResponse<List<Answer>> apiResponse;
        try {
            List<Answer> answers = answerService.getAllAnswersByQuestionId(questionId);
            apiResponse = new ApiResponse<>(answers);
        }
        catch(Exception e) {
            apiResponse = new ApiResponse<>("404", "No answers found");
        }
        return apiResponse;
    }

    @PostMapping("/updateUpvotes")
    public ApiResponse<Integer> updateUpvotes(@RequestParam("userId") String userId, @RequestParam("answerId") String answerId) {
        ApiResponse<Integer> apiResponse;
        try {
            int upvotes = answerService.updateUpvotes(userId, answerId);
            apiResponse = new ApiResponse<>(upvotes);
        }
        catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not update");
        }
        return apiResponse;
    }

    @PostMapping("/updateDownvotes")
    public ApiResponse<Integer> updateDownvotes(@RequestParam("userId") String userId, @RequestParam("answerId") String answerId) {
        ApiResponse<Integer> apiResponse;
        try {
            int downvotes = answerService.updateDownvotes(userId, answerId);
            apiResponse = new ApiResponse<>(downvotes);
        }
        catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not update");
        }
        return apiResponse;
    }

    @GetMapping("/getAllAnswers")
    public List<Answer> getAllAnswerByUserId(@RequestParam("userId") String userId) {
        return new ArrayList<Answer>(answerRepository.findAllByUserId(userId));
    }

    @GetMapping("/getAllQuestions")
    public List<Question> getAllQuestionByUserId(@RequestParam("userId") String userId) {
        return new ArrayList<Question>(questionRepository.findAllByUserId(userId));
    }

    @GetMapping("/getPoints")
    public int getPoints(@RequestParam("userId") String userId) {
        List<Answer> answers = answerRepository.findAllByUserId(userId);
        int count = answers.size()*5;
        for(Answer answer: answers) {
            count += answer.getUpvotes();
            count -= answer.getDownvotes();
        }
        List<Question> questions = questionRepository.findAllByUserId(userId);
        count += questions.size()*10;
        Profile profile = profileFeign.findById(userId);
        profile.setPoints(count);
        return count;
    }

    @GetMapping("/profileClassification")
    public String profileClassifier(@RequestParam("userId") String userId) {
        Profile profile = profileFeign.findById(userId);
        int points = profile.getPoints();
        if(points>=6000)
            return "Platinum User";
        else if(points>=2501 && points<=6000)
            return "Gold user";
        else if(points>=1001 && points<=2500)
            return "Silver user";
        else return "Beginner";
    }
}
