package com.example.Project2.Service;


import com.example.Project2.infra.FeingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeingService {
    @Autowired
    private FeingRepository feingRepository;
    public Boolean tokenValdition(String tkn){
        return feingRepository.tokenValidation(tkn);
    }
    public String tokenTypeUser(String tkn){
        return feingRepository.tokenTypeUser(tkn);
    }
}
