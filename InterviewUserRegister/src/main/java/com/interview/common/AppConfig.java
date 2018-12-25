package com.interview.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.interview.service.IAvatarService;
import com.interview.service.IExclusionService;
import com.interview.service.impl.AvatarServiceImpl;
import com.interview.service.impl.ExclusionServiceImpl;

@Configuration
public class AppConfig {

	@Bean
    public IAvatarService avatarService() {
        return new AvatarServiceImpl();
    }

	@Bean
    public IExclusionService exclusionService() {
        return new ExclusionServiceImpl();
    }
}
