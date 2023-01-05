package com.example.Project2.Service.ResponseHandler;

public class ResponseStatusErrorException extends RuntimeException{

    public ResponseStatusErrorException(String msg){
        super(msg);
    }
}
