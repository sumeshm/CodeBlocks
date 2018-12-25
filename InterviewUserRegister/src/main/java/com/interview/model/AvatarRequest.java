package com.interview.model;

public class AvatarRequest {

	private String userName;

	private String password;

	// ISO 8601
	private String dob;

	// http://en.wikipedia.org/wiki/Social_Security_number
	private String ssn;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "{ userName:" + userName + ", dobDate:" + dob + ", ssn:" + ssn + " }";
	}
}
