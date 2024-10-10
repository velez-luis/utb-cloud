package com.cloud.utb.project.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
	
	//Header
	private ResponseCodeEnum codeEnum;
	private Integer code;
	private String message;
	private String uri;
	private LocalDateTime dateTime;
	//Body
	private Object response;
	
}
