package com.example.feedservice.FeignHandler;

import com.example.feedservice.ApiHandler.ApiResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class ProfileFallback implements FallbackFactory<ProfileFeign> {

    @Override
    public ProfileFeign create(Throwable throwable) {
        return new ProfileFeign() {

            @Override
            public void updatePoints(String profileId, int points) {
            }

            @Override
            public ApiResponse<List<String>> getCategoriesByUserId(@RequestParam("userId") String userId){
                return new ApiResponse<>();
            }
        };
    }
}
