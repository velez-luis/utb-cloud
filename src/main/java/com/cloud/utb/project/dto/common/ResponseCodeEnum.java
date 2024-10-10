package com.cloud.utb.project.dto.common;

public enum ResponseCodeEnum {
	
	SUCCESS(1, "SUCCESS"),
	WARNING(2, "WARNING"),
	ERROR(3, "ERROR");
	
	private Integer responseCode;
	
	private String responseMessage;

	private ResponseCodeEnum(Integer responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}


}
