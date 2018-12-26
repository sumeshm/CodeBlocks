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
	// 1. make min and max length configurable via properties file
	// 2. 

	public static void main(String[] args) {
		 SpringApplication.run(BootApplication.class, args);
	}
}
