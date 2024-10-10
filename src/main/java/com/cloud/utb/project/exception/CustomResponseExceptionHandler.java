package com.cloud.utb.project.exception;

import com.cloud.utb.project.config.GlobalConstants;
import com.cloud.utb.project.dto.common.CustomResponse;
import com.cloud.utb.project.dto.common.ResponseCodeEnum;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        CustomResponse response = new CustomResponse();
        response.setCode(ex.getStatusCode().value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        response.setMessage(request.getDescription(true));
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    /**
     * @param ex      the exception to handle
     * @param headers the headers to use for the response
     * @param status  the status code to use for the response
     * @param request the current request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomResponse response = new CustomResponse();
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    /**
     * @param ex      the exception to handle
     * @param headers the headers to use for the response
     * @param status  the status code to use for the response
     * @param request the current request
     * @return
     */
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomResponse response = new CustomResponse();
        response.setCode(HttpStatus.CONFLICT.value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * @param ex      the exception to handle
     * @param headers the headers to use for the response
     * @param status  the status code to use for the response
     * @param request the current request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomResponse response = new CustomResponse();
        response.setCode(ex.getStatusCode().value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomResponse response = new CustomResponse();
        response.setCode(ex.getStatusCode().value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<CustomResponse> handleEntityNotFoundException(EntityAlreadyExistException ex, WebRequest request) {
        CustomResponse response = new CustomResponse();
        response.setCode(ex.getStatusCode().value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setMessage(GlobalConstants.ENTITY_ALREADY_EXISTS);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        CustomResponse response = new CustomResponse();
        response.setCode(ex.getStatusCode().value());
        response.setCodeEnum(ResponseCodeEnum.ERROR);
        response.setDateTime(LocalDateTime.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatusCode());
//        return new ResponseEntity<>(err, ex.getStatusCode());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomErrorResponse> handleSQLException(SQLException ex, WebRequest req) {
        CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StringBuilder errorMsgBuilder = new StringBuilder();
        ex.getFieldErrors().forEach(error -> {
            String fieldName = error.getField().toUpperCase();
            String errorMessage = error.getDefaultMessage();
            errorMsgBuilder.append(fieldName)
                    .append(": ")
                    .append(errorMessage)
                    .append("|");
        });
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), errorMsgBuilder.toString(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
