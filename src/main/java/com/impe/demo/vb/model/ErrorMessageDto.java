package com.impe.demo.vb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorMessageDto {

	private Long id;
	private Date timestamp = new Date();
	private String message;
	private List<String> details = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ErrorMessageDto [id=" + id + ", timestamp=" + timestamp + ", message=" + message + ", details="
				+ details + "]";
	}
	
	
	
	
}
