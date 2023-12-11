package com.example.feedservice.FeignHandler;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.entity.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "Profile",url = "http://localhost:8088",fallbackFactory = ProfileFallback.class)
public interface ProfileFeign {

    @RequestMapping(method = RequestMethod.GET,value="/profile/")
    Profile findById(@RequestParam("profileId") String profileId);
}
