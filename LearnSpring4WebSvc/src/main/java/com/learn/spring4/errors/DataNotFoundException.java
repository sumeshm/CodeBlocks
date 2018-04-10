package com.learn.spring4.errors;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 749269166534265923L;

	public DataNotFoundException(String msg)
	{
		super(msg);
	}
}
