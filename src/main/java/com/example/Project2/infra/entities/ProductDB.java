package com.example.Project2.infra.entities;

import com.example.Project2.Dominio.Product;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class ProductDB extends Product {
    public ProductDB(Long codigo, String descripton, Float price, Integer amount) {
        super(codigo, descripton, price, amount);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long codigo;
    @Column(name = "description", nullable = false)
     private String description;

    @Column(name = "price", nullable = false)
     private Float price;

    @Column(name = "amount",  nullable = false)
     private Integer amount;

}
