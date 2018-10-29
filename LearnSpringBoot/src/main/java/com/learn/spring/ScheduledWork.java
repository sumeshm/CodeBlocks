package com.learn.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledWork {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledWork.class);

	// "0    0  *    *  *  *" = the top of every hour of every day.
	// "*/10 *  *    *  *  *" = every ten seconds.
	// "0    0  6,19 *  *  *" = 6:00 AM and 7:00 PM every day.
	// "0    0  6    *  *  *" = 6:00 AM every day.
	// "30   *  *    *  *  *" = every 30 seconds.
	// "0 0/60 * * * ?" = every hour

//	@Scheduled(cron = "0 0 * ? * * *")
//	public void doWork() {
//		logger.info("****** DO WORK 0 ****** : ");
//	}

//	@Scheduled(cron = "0 0 0/1 1/1 * ? *")
//	public void doWork1() {
//		logger.info("****** DO WORK 1 ****** : ");
//	}
	
	@Scheduled(cron = "0 3 18 *  *  *")
	public void doWork2() {
		logger.info("****** DO WORK 2 ****** : ");
	}
	
	@Scheduled(cron = "0 0/60 * * * ?")
	public void doWork3() {
		logger.info("****** DO WORK 3 ****** : ");
	}
}
