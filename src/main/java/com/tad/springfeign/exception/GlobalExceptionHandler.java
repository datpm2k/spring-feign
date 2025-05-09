package com.tad.springfeign.exception;

import com.tad.springfeign.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.ok(new BaseResponse<>("404", ex.getMessage(), new Object()));
    }

    @ExceptionHandler(SystemErrorException.class)
    public ResponseEntity<BaseResponse<Object>> handleSystemErrorException(SystemErrorException ex) {
        return ResponseEntity.ok(new BaseResponse<>("500", ex.getMessage(), new Object()));
    }
}
