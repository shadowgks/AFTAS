package com.example.appgcm.exception;


import com.example.appgcm.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Response<?>> handleIllegalArgumentException(Exception ex){
        Response<?> response = new Response<>();
        String getErrorMessage = ex.getMessage();
        response.setError(getErrorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Response<?>> handleArgumentNotValid(MethodArgumentNotValidException requests){
        Response<?> response = new Response<>();
        Map<String, String> errors = new HashMap<>();
        requests.getBindingResult().getFieldErrors().forEach(
                e -> {
                    errors.put(e.getField(), e.getDefaultMessage());
                }
        );
        response.setErrorsValidation(errors);
        return ResponseEntity.badRequest().body(response);
    }
}
