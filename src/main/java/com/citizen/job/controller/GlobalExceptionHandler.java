package com.citizen.job.controller;

import com.citizen.job.model.ErrorDetails;
import com.citizen.job.response.ApiResponse;
import com.citizen.job.utils.JobNotFoundException;
import com.citizen.job.utils.UserUnauthorizedException;
import com.citizen.job.utils.VolunteerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toEpochMilli(), request.getDescription(false), ex.getMessage());

        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Object> apiResponse = ApiResponse.error(errorDetails, ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleJobNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toEpochMilli(), request.getDescription(false), ex.getMessage());

        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Object> apiResponse = ApiResponse.error(errorDetails, ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleVolunteerNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toEpochMilli(), request.getDescription(false), ex.getMessage());

        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Object> apiResponse = ApiResponse.error(errorDetails, ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserUnauthorizedException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toEpochMilli(), request.getDescription(false), ex.getMessage());

        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Object> apiResponse = ApiResponse.error(errorDetails, ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
