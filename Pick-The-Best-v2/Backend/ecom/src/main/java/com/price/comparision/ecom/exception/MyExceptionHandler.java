package com.price.comparision.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userException(UserNotFoundException exception) {
       return new ResponseEntity<>("user not exist in system", HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> authoriseException(InvalidCredentialsException exception) {
       return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<Object> invalidOTP(InvalidOtpException exception) {
       return new ResponseEntity<>("invalid OTP", HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(GeneralBusinessException.class)
    public ResponseEntity<Object> generalBusinessException(GeneralBusinessException exception) {
       return new ResponseEntity<>(exception.getMessage(), exception.getErrorResponse().getStatus());
    }
    
}
