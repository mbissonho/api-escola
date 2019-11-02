package com.escola.api.exception;

public class SystemError {

	private String userMessage;
	private String devMessage;
	
	public SystemError(String userMessage, String devMessage) {
		this.userMessage = userMessage;
		this.devMessage = devMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getDevMessage() {
		return devMessage;
	}
	
	
}
