package com.learn.spring.health;

import java.time.LocalTime;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CarHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = 0;
		LocalTime now = LocalTime.now();
		if (now.getMinute() % 2 != 0) {
			errorCode = 1;
		}
		if (errorCode != 0) {
			return Health.up().withDetail("Engine started", errorCode).build();
		}
		return Health.down().withDetail("Engine stopped", errorCode).build();
	}

}
