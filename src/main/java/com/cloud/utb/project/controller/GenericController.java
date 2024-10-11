package com.cloud.utb.project.controller;

import com.cloud.utb.project.dto.common.CustomResponse;
import com.cloud.utb.project.dto.common.ResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class GenericController {

    protected ResponseEntity<CustomResponse> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<CustomResponse> ok(Object obj){
        return ResponseEntity.ok(CustomResponse
                .builder()
                .dateTime(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .codeEnum(ResponseCodeEnum.SUCCESS)
                .message(ResponseCodeEnum.SUCCESS.name())
                .response(obj)
                .build());
    }

    protected ResponseEntity<CustomResponse> created(Object obj){
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomResponse
                .builder()
                .dateTime(LocalDateTime.now())
                .code(HttpStatus.CREATED.value())
                .codeEnum(ResponseCodeEnum.SUCCESS)
                .message(ResponseCodeEnum.SUCCESS.name())
                .response(obj)
                .build());
    }
}
