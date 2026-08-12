package com.farrukh.ecommerce.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest request){
        HttpStatus status=HttpStatus.CONFLICT;
        ErrorResponse errorResponse=buildErrorResponse(status,ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex, HttpServletRequest request){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse=buildErrorResponse(status,ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(HttpStatus status, String message,String path){
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(path)
                .message(message)
                .build();

 
            }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex, HttpServletRequest request){
        HttpStatus status=HttpStatus.UNAUTHORIZED;
        ErrorResponse errorResponse=buildErrorResponse(status,ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errorResponse);
    }
    @ExceptionHandler(CategoryAlreadyExistsException.class)
public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(
        CategoryAlreadyExistsException ex,
        HttpServletRequest request) {

    HttpStatus status = HttpStatus.CONFLICT;

    ErrorResponse errorResponse = buildErrorResponse(
            status,
            ex.getMessage(),
            request.getRequestURI()
    );

    return ResponseEntity
            .status(status)
            .body(errorResponse);
}
@ExceptionHandler(CategoryNotFoundException.class)
public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex,HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse errorResponse = buildErrorResponse(
            status,
            ex.getMessage(),
            request.getRequestURI()
    );

    return ResponseEntity
            .status(status)
            .body(errorResponse);
}
}
