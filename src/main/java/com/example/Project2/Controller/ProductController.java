package com.example.Project2.Controller;

import com.example.Project2.Service.FeingService;
import com.example.Project2.Service.ProductService;
import com.example.Project2.Exception.ResponseHandler.Forbiden;
import com.example.Project2.infra.entities.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FeingService feingService;
    @PostMapping
    @RequestMapping("product/register")
    public ProductDB ProductResgister(@RequestBody  ProductDB  productDB, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente")){
                throw new RuntimeException("Acesso Invalido");
            }
            return productService.save(productDB);
        }
        throw new RuntimeException("Token Invalido");
    }
    @GetMapping
    @RequestMapping("/products")
    public List<ProductDB> ProductList(@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            return productService.listProducts();
        }
        throw new RuntimeException("Token Invalido");
    }

    @GetMapping
    @RequestMapping("/products/{id}")
    public ProductDB ProductId(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            return productService.listFilterId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        }
        throw new RuntimeException("Token Invalido");
    }
    @GetMapping
    @RequestMapping("/products/type/{type}")
    public List<ProductDB> FilterDescription (@PathVariable("type") String type, Pageable pageable, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            return productService.filterType(type, pageable);
        }
        throw new RuntimeException("Token Invalido");
    }
    @GetMapping
    @RequestMapping("/products/pagination")
    public Page<ProductDB> ListAlunosPagina(Pageable pageable, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            return productService.paginacao(pageable);
        }
        throw new RuntimeException("Token Invalido");
    }

    @GetMapping
    @RequestMapping("/products/description/{description}")
    public Page<ProductDB> filterDescription(@PathVariable("description") String description, Pageable pageable,@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn ){
        if(feingService.tokenValdition(tkn)){
            return productService.findDesc(description, pageable);
        }
        throw new RuntimeException("Token Invalido");
    }
   /* @GetMapping
    @RequestMapping("/products/price/{price}")
    public Page<ProductDB> filterPrice(@PathVariable("price") String price, Pageable pageable){
        Float price2 = Float.parseFloat(price);
        return productService.findPrice(price2, pageable);
    }

    */
    @PatchMapping
    @RequestMapping("/product/update/{id}/price/{price}")
    public ProductDB UpdatePrice(@PathVariable("id") Long id, @PathVariable("price") Float price, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn ){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente")){
                throw new Forbiden();
            }
            return productService.updatePrice(id, price);
        }
        throw new RuntimeException("Token Invalido");
    }

    @PatchMapping
    @RequestMapping("/product/update/{id}/amount/{amount}")
    public ProductDB UpadateAmount(@PathVariable("id")Long id, @PathVariable("amount") Integer amount,  @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente")){
                throw new Forbiden();
            }
            return productService.updateAmount(id,amount);
        }
        throw new RuntimeException("Token Invalido");

    }
    @PutMapping
    @RequestMapping("/product/update/{codigo}")
    public ProductDB Update(@PathVariable("codigo") Long id, @RequestBody ProductDB productDB, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            return productService.editById(id, productDB);
        }
        throw new RuntimeException("Token Invalido");
    }


    @DeleteMapping
    @RequestMapping("/product/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity Delete(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            ProductId(id, tkn);
            productService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        throw new RuntimeException("Token Invalido");
    }

}
