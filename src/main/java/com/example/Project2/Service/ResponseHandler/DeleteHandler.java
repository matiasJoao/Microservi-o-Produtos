package com.example.Project2.Service.ResponseHandler;

import com.example.Project2.infra.entities.ProductDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class DeleteHandler {
    private String cod;
    private String mensage;
    private HttpStatus httpStatus;
    private Date Date;

    private Optional<ProductDB> product;

}
