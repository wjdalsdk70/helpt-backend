package com.HELPT.Backend.global.error;

import com.HELPT.Backend.global.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.HELPT.Backend.global.error.ErrorCode.NOT_EXIST_DATA;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
        log.error("[CustomException] errCode : " + ex.getErrorCode());
        log.error("[CustomException] errMsg : " + ex.getMessage());
        if (ex.getErrorCode() == NOT_EXIST_DATA){
            return ResponseEntity.status(ex.getErrorCode().getHttpStatus().value()).body(null);
        }
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus().value()).body(ApiResponse.failResponse(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        log.error("[RuntimeException] errMsg : " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.errorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        log.error("[Exception] errMsg : " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.errorResponse(ex.getMessage()));
    }
}
