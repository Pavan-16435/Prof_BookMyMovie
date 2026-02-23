package com.movie.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.movie.Model.ResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseMessage> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(403)
                .body(new ResponseMessage(403, ex.getMessage(), null));
    }
}
