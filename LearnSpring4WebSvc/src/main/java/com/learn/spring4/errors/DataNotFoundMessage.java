package com.learn.spring4.errors;

public class DataNotFoundMessage {

	private String errorMessage;
	private String erorCode;

	// needed for JAX-RS scan
	DataNotFoundMessage()
	{
		
	}

	DataNotFoundMessage(String msg, String code)
	{
		this.errorMessage = msg;
		this.erorCode = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErorCode() {
		return erorCode;
	}

	public void setErorCode(String erorCode) {
		this.erorCode = erorCode;
	}
}
