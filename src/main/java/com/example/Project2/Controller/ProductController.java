package com.example.Project2.Controller;

import com.example.Project2.Service.ProductService;
import com.example.Project2.infra.entities.ProductDB;
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
    public ProductDB ProductResgister(@RequestBody  ProductDB  productDB){
        return productService.save(productDB);
    }
    @GetMapping
    @RequestMapping("/products")
    public List<ProductDB> ProductList(){
        return productService.listProducts();
    }

    @GetMapping
    @RequestMapping("/products/{id}")
    public ProductDB ProductId(@PathVariable Long id){
        return productService.listFilterId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
    @GetMapping
    @RequestMapping("/products/type/{type}")
    public List<ProductDB> FilterDescription (@PathVariable("type") String type, Pageable pageable){
        return productService.filterType(type, pageable);
    }
    @GetMapping
    @RequestMapping("/products/pagination")
    public Page<ProductDB> ListAlunosPagina(Pageable pageable){
        return productService.paginacao(pageable);
    }

    @GetMapping
    @RequestMapping("/products/description/{description}")
    public Page<ProductDB> filterDescription(@PathVariable("description") String description, Pageable pageable){
        return productService.findDesc(description, pageable);
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
    public ProductDB UpdatePrice(@PathVariable("id") Long id, @PathVariable("price") Float price){
        return productService.updatePrice(id, price);
    }

    @PatchMapping
    @RequestMapping("/product/update/{id}/amount/{amount}")
    public ProductDB UpadateAmount(@PathVariable("id")Long id, @PathVariable("amount") Integer amount){
        return productService.updateAmount(id,amount);
    }
    @PutMapping
    @RequestMapping("/product/update/{codigo}")
    public ProductDB Update(@PathVariable("codigo") Long id, @RequestBody ProductDB productDB){
        return productService.editById(id, productDB);
    }


    @DeleteMapping
    @RequestMapping("/product/delete/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        ProductId(id);
        return productService.delete(id);
    }

}
