package com.example.feedservice.FeignHandler;

import com.example.feedservice.ApiHandler.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "Profile",url = "http://10.20.3.163:8088", fallbackFactory = ProfileFallback.class)
public interface ProfileFeign {

    @PutMapping("/quora/profile/updatePoints")
    void updatePoints(@RequestParam("profileId") String profileId, @RequestParam("points") int points);

    @GetMapping("/quora/profile/getCategoriesByUserId")
    ApiResponse<List<String>> getCategoriesByUserId(@RequestParam("userId") String userId);
}
