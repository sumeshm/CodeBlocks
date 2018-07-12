package com.learn.spring4.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * ApplicationInitializer: 
 * - loaded at the startup of the application
 * - provide the configuration class along with the context URL
 */

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		return new Class[] { ServletConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		return null;
	}

	@Override
	protected String[] getServletMappings() 
	{
		return new String[] { "/*" };
	}
}
