package com.example.Project2.Controller;

import com.example.Project2.Service.ProductService;
import com.example.Project2.infra.entities.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @RequestMapping("product/register")
    public ResponseEntity ProductResgister(@RequestBody ProductDB productDB){
        return productService.save(productDB);
    }
    @GetMapping
    @RequestMapping("/products")
    public List<ProductDB> ProductList(){
        return productService.ListProducts();
    }

}
