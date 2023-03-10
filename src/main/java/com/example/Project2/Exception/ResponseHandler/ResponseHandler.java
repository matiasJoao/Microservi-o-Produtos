package com.example.Project2.Exception.ResponseHandler;

import lombok.*;
import org.springframework.http.HttpStatus;


import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ResponseHandler {
    private String cod;
    private String mensage;
    private HttpStatus httpStatus;
    private Date Date;

}
