package com.interview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AvatarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long avatarId;

	private String userName;

	private String password;

	// ISO 8601 - yyyy-MM-dd'T'HH:mm:ss.SSSXXX
	private String dob;

	// http://en.wikipedia.org/wiki/Social_Security_number
	private String ssn;

	public Long getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Long avatarId) {
		this.avatarId = avatarId;
	}

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
		return "{ avatarId:" + avatarId + ", userName:" + userName + ", dobDate:" + dob + ", ssn:" + ssn + " }";
	}
}
