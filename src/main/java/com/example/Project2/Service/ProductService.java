package com.example.Project2.Service;

import com.example.Project2.Service.ResponseHandler.ResponseHandler;
import com.example.Project2.infra.ProductRepository;
import com.example.Project2.infra.entities.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    ResponseHandler responseHandler;

    public ResponseEntity save(ProductDB productDB) {
        productRepository.save(productDB);
        responseHandler = new ResponseHandler("201", "Cadastrado", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseHandler);
    }

    public List<ProductDB> ListProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDB> ListFilterId(Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity Delete(Long id) {
        productRepository.deleteById(id);
        responseHandler = new ResponseHandler("200", "Deletado", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandler);
    }

    public ProductDB EditById(Long id, ProductDB productDB) {
        return productRepository.save(productDB);
    }

    public List<ProductDB> FilterDescription(String description) {
        return productRepository.findByDescription(description);
    }

    public ProductDB UpdatePrice(Long id, Float price) {

        ProductDB productDB = productRepository.findByCodigo(id);
        productDB.setPrice(price);
        return productRepository.save(productDB);
    }
    public Page<ProductDB> Paginacao(Pageable pageable){
      return productRepository.findAll(pageable);
    }

}

