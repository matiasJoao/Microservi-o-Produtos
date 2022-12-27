package com.example.Project2.Service.Exception;

import com.example.Project2.Service.ResponseHandler.ResponseHandler;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.net.ConnectException;

@ControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullExceptionHandler(NullPointerException e){
        ResponseHandler error = new ResponseHandler("400", "invalid body", HttpStatus.BAD_REQUEST);
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationHandler(ConstraintViolationException e){
        ResponseHandler error = new ResponseHandler("400", "empty body", HttpStatus.BAD_REQUEST);
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity ConnectExceptionHandler(ConnectException e){
        ResponseHandler error = new ResponseHandler("500", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        return new  ResponseEntity (error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
