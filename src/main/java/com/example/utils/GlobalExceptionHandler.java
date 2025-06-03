package com.example.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final AppLogger logger = AppLogger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(HttpServletRequest request, Exception e) {
        logger.error(GlobalExceptionHandler.class.getName(), "Exception Caught: ", "testID", e);

        ErrorResponseWrapper errorResponse = ErrorResponseWrapper.builder()
                .errorCode(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
