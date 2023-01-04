package com.example.Project2.Service.ResponseHandler;

import com.example.Project2.infra.entities.ProductDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class DeleteHandler {
    private String cod;
    private String mensage;
    private HttpStatus httpStatus;
    private Date Date;
    private String description;
    private String type;
    private Float price;
    private Integer amount;

}
