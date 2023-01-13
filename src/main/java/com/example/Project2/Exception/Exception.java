package com.example.Project2.Exception;


import com.example.Project2.DTO.Response;
import com.example.Project2.Exception.ResponseHandler.Forbiden;
import com.example.Project2.Exception.ResponseHandler.ResponseHandler;
import javax.validation.ConstraintViolationException;

import com.example.Project2.Exception.ResponseHandler.ResponseStatusErrorException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;


@ControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullExceptionHandler(NullPointerException e){
        ResponseHandler error = new ResponseHandler("400", "invalid body", HttpStatus.BAD_REQUEST, new Date());
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationHandler(ConstraintViolationException e){
        ResponseHandler error = new ResponseHandler("400", "empty body", HttpStatus.BAD_REQUEST, new Date());
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity ConnectExceptionHandler(ConnectException e){
        ResponseHandler error = new ResponseHandler("500", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return new  ResponseEntity (error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityException(SQLIntegrityConstraintViolationException e){
        ResponseHandler error = new ResponseHandler("400", "empty body", HttpStatus.BAD_REQUEST, new Date());
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResponseStatusErrorException.class)
    public ResponseEntity responseCampoVazio(ResponseStatusErrorException e){
        ResponseHandler error = new ResponseHandler("400", "empty args", HttpStatus.BAD_REQUEST, new Date());
        return new  ResponseEntity (error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handlerRunTimeE(RuntimeException e){
        Response error = new Response(e.getMessage(),"401",  HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity FeingExceptionHandler(FeignException e){
        Response error = new Response("Token Invalido", "401", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(Forbiden.class)
    public ResponseEntity ForbindHandler(Forbiden e){
        Response error = new Response("Acesso Invaldio", "403", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


}
