package com.interview.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.interview.dao.AvatarDaoService;
import com.interview.service.IAvatarDaoService;
import com.interview.service.IRegistrationService;
import com.interview.service.IExclusionService;
import com.interview.service.impl.RegistrationServiceImpl;
import com.interview.service.impl.ExclusionServiceImpl;
import com.interview.service.util.AvatarValidator;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
    public IRegistrationService registrationService() {
        return new RegistrationServiceImpl();
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

	@Bean
	public Map<String, String> blacklistMap() {
		return new HashMap<>();
	}

	@Bean
	public Map<String, Long> avatarMap() {
		return new HashMap<>();
	}
}
