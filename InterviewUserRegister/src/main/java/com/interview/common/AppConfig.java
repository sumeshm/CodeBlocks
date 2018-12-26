package com.interview.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.interview.dao.AvatarDaoService;
import com.interview.service.IAvatarDaoService;
import com.interview.service.IAvatarService;
import com.interview.service.IExclusionService;
import com.interview.service.impl.AvatarServiceImpl;
import com.interview.service.impl.ExclusionServiceImpl;
import com.interview.service.util.AvatarValidator;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
    public IAvatarService avatarService() {
        return new AvatarServiceImpl();
    }

	@Bean
	@Scope("singleton")
    public IExclusionService exclusionService() {
        return new ExclusionServiceImpl();
    }

	@Bean
	@Scope("singleton")
    public IAvatarDaoService avatarDaoService() {
		return new AvatarDaoService();
	}

	@Bean
	@Scope("singleton")
    public AvatarValidator avatarValidator() {
		return new AvatarValidator();
	}
}
