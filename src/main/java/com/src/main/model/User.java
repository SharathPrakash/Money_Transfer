package com.src.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonIgnore
	private long userId;

	@JsonProperty(required = true)
	private String userName;

	@JsonProperty(required = true)
	private String emailAddress;

	public User() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public User(String userName, String emailAddress) {
		super();
		this.userName = userName;
		this.emailAddress = emailAddress;
	}

	public User(long userId, String userName, String emailAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailAddress = emailAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", emailAddress=" + emailAddress + "]";
	}

}
