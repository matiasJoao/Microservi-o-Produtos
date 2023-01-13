package com.example.Project2.Exception.ResponseHandler;

public class ResponseStatusErrorException extends RuntimeException{

    public ResponseStatusErrorException(String msg){
        super(msg);
    }
}
