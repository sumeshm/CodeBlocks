package com.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	// Assumptions:
	// 1. Password: can not be all Upper case characters, has to be mix of upper, lower case characters and numbers
	// 2. Password: max length of 8 characters
	// 3. UserName: minimum length is 4, same as password
	// 4. UserName: max length of 8 characters
	// 5. 

	// TODO:
	// 1. unclear if an already registered avatar, should be un-registered when blacklisted
	// 2. unclear why both SSN and DOB is needed for blacklist, since these two maintain one-is-to-one relation
	// 3. compare DOB as Date objects instead of String
	// 4. add security headers
	// 5. need to use a DB, and find a way to package it along with the boot-app

	public static void main(String[] args) {
		 SpringApplication.run(BootApplication.class, args);
	}
}
