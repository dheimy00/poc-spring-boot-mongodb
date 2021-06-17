package com.api.exceptions.general;

import java.time.LocalDateTime;

public class ExceptionDetails {
	
	protected String title;

	protected int status;

	protected String detail;

	protected LocalDateTime timeStamp;

	protected String developerMessage;
	
	public ExceptionDetails() {
	}
	
	
	public ExceptionDetails(String title, int status, String detail, LocalDateTime timeStamp, String developerMessage) {
		super();
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.timeStamp = timeStamp;
		this.developerMessage = developerMessage;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
	

}
