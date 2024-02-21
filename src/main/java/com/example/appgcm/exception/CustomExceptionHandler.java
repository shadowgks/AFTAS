package com.example.appgcm.exception;

import com.example.appgcm.exception.custom.CustomSecurityException;
import com.example.appgcm.utils._Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialsException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        problemDetail.setProperty("access_denied", "Authentication Failure");
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail accessDeniedException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        problemDetail.setProperty("access_denied", "Unauthorized Failure");
        return problemDetail;
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail signatureExceptionException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        problemDetail.setProperty("access_denied", "JWT Signature not valid!");
        return problemDetail;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail expiredJwtExceptionException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        problemDetail.setProperty("access_denied", "JWT token already expired!");
        return problemDetail;
    }
}
