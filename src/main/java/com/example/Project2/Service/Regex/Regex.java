package com.example.Project2.Service.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public Boolean description(String description){
        Pattern pattern = Pattern.compile("[^\\ ]+");
        Matcher matcher = pattern.matcher(description);
        Boolean verify;
        if(matcher.matches()){
            verify = false;
        }
        else{
            verify = true;
        }
        return verify;
    }
}
