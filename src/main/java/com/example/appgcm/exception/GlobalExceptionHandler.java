package com.example.appgcm.exception;


import com.example.appgcm.utils._Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<_Response<?>> handleIllegalArgumentException(Exception ex){
        _Response<?> response = new _Response<>();
        String getErrorMessage = ex.getMessage();
        response.setError(getErrorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<_Response<?>> handleArgumentNotValid(MethodArgumentNotValidException requests){
        _Response<?> response = new _Response<>();
        Map<String, String> errors = new HashMap<>();
        requests.getBindingResult().getFieldErrors()
                .forEach(e ->
                        errors.put(e.getField(), e.getDefaultMessage()));
        response.setErrorsValidation(errors);
        return ResponseEntity.badRequest().body(response);
    }

}
