package com.learn.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/*
 * ApplicationConfiguration:
 * - point out servlet-container
 * - point out the packages to load
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.learn.spring4")
public class ServletConfiguration {

}
