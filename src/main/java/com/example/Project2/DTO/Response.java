package com.example.Project2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String mensagem;
    private String codigo;
    private HttpStatus httpStatus;
}
