package com.example.feedservice.FeignHandler;


import com.example.feedservice.dto.SearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SearchEntity",url = "http://10.20.3.177:8089", fallbackFactory = ProfileFallback.class)
public interface SolrFeign {

    @PutMapping("/quora/search/update-question")
    void updateQuestion(@RequestParam("questionId") String questionId, @RequestBody SearchDto searchDto);

    @PostMapping("/quora/search/addQuestion")
    void saveQuestion(@RequestBody SearchDto questionDto);
}
