package com.example.feedservice.FeignHandler;

import com.example.feedservice.ApiHandler.ApiResponse;
import com.example.feedservice.entity.Profile;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProfileFallback implements FallbackFactory<ProfileFeign> {

    @Override
    public ProfileFeign create(Throwable throwable) {
        return new ProfileFeign() {

            @Override
            public Profile findById(String profileId) {
                return null;
            }
        };
    }
}
