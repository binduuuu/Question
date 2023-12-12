package com.example.feedservice.FeignHandler;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProfileFallback implements FallbackFactory<ProfileFeign> {

    @Override
    public ProfileFeign create(Throwable throwable) {
        return new ProfileFeign() {

            @Override
            public void updatePoints(String profileId, int points) {
            }
        };
    }
}
