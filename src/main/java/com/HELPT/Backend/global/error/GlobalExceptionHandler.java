package com.HELPT.Backend.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        log.error("[CustomException] errCode : " + ex.getErrorCode());
        log.error("[CustomException] errMsg : " + ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(ex.getErrorCode().getHttpStatus().value(), ex.getMessage()),
                ex.getErrorCode().getHttpStatus()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error("[RuntimeException] errMsg : " + ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("[Exception] errMsg : " + ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
