package com.example.feedservice.FeignHandler;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "Profile",url = "http://10.20.3.163:8088", fallbackFactory = ProfileFallback.class)
public interface ProfileFeign {

    @PutMapping("/quora/profile/updatePoints")
    void updatePoints(@RequestParam("profileId") String profileId, @RequestParam("points") int points);
}
