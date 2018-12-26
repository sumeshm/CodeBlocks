package com.interview.common;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:app.properties")
@Scope("singleton")
public class AppProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppProperties.class);

	private int userNameMinLength;
	private int userNameMaxLength;
	private int passwordMinLength;
	private int passwordMaxLength;
	private String dobFormat;
	private String ssnRegex;

	@PostConstruct
	private void logData() {
		LOGGER.info("MIN_LENGTH_USER_NAME=" + userNameMinLength);
		LOGGER.info("MAX_LENGTH_USER_NAME=" + userNameMaxLength);
		LOGGER.info("MIN_LENGTH_PASSWORD=" + passwordMinLength);
		LOGGER.info("MAX_LENGTH_PASSWORD=" + passwordMaxLength);
	}

	public int getUserNameMinLength() {
		return userNameMinLength;
	}

	public void setUserNameMinLength(int userNameMinLength) {
		this.userNameMinLength = userNameMinLength;
	}

	public int getUserNameMaxLength() {
		return userNameMaxLength;
	}

	public void setUserNameMaxLength(int userNameMaxLength) {
		this.userNameMaxLength = userNameMaxLength;
	}

	public int getPasswordMinLength() {
		return passwordMinLength;
	}

	public void setPasswordMinLength(int passwordMinLength) {
		this.passwordMinLength = passwordMinLength;
	}

	public int getPasswordMaxLength() {
		return passwordMaxLength;
	}

	public void setPasswordMaxLength(int passwordMaxLength) {
		this.passwordMaxLength = passwordMaxLength;
	}

	public String getDobFormat() {
		return dobFormat;
	}

	public void setDobFormat(String dobFormat) {
		this.dobFormat = dobFormat;
	}

	public String getSsnRegex() {
		return ssnRegex;
	}

	public void setSsnRegex(String ssnRegex) {
		this.ssnRegex = ssnRegex;
	}
}
