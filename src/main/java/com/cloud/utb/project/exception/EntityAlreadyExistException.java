package com.cloud.utb.project.exception;

import com.cloud.utb.project.config.GlobalConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class EntityAlreadyExistException extends ErrorResponseException {

    public EntityAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
        problemDetail.setTitle(GlobalConstants.ENTITY_ALREADY_EXISTS);
        return problemDetail;
    }
}
