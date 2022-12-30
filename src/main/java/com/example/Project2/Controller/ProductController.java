package com.example.Project2.Controller;

import com.example.Project2.Service.ProductService;
import com.example.Project2.infra.entities.ProductDB;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @RequestMapping("product/register")
    public ResponseEntity ProductResgister(@RequestBody @Valid ProductDB  productDB){
        return productService.save(productDB);
    }
    @GetMapping
    @RequestMapping("/products")
    public List<ProductDB> ProductList(){
        return productService.ListProducts();
    }

    @GetMapping
    @RequestMapping("/products/{id}")
    public ProductDB ProductId(@PathVariable Long id){
        return productService.ListFilterId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
    @GetMapping
    @RequestMapping("/products/type/{type}")
    public List<ProductDB> FilterDescription (@PathVariable String type){
      return productService.FilterType(type);
    }
    @GetMapping
    @RequestMapping("/products/pagination")
    public Page<ProductDB> ListAlunosPagina(Pageable pageable){
       return productService.Paginacao(pageable);
    }
    @PatchMapping
    @RequestMapping("/product/update/price/{id}/{price}")
    public ProductDB UpdatePrice(@PathVariable("id") Long id, @PathVariable("price") Float price){
        return productService.UpdatePrice(id, price);
    }

    @PatchMapping
    @RequestMapping("/product/update/amount/{id}/{amount}")
    public ProductDB UpadateAmount(@PathVariable("id")Long id, @PathVariable("amount") Integer amount){
        return productService.UpdateAmount(id,amount);
    }
    @PutMapping
    @RequestMapping("/product/update/{codigo}")
    public ProductDB Update(@PathVariable("codigo") Long id, @RequestBody ProductDB productDB){
        return productService.EditById(id, productDB);
    }


    @DeleteMapping
    @RequestMapping("/product/delete/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        ProductId(id);
        return productService.Delete(id);
    }

}
