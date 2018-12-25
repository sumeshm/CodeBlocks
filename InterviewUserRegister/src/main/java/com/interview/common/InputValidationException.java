package com.interview.common;

public class InputValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errMsg;
	private String inputName;
	private String inputValue;

	public InputValidationException(String inputName, String inputValue, String errMsg) {
		super(errMsg);
		this.inputName = inputName;
		this.inputValue = inputValue;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getMessage() {
		return "InputValidationException: " + super.getMessage() + "; for " + inputName + ":" + inputValue;
	}
}
