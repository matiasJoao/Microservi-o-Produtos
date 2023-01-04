package com.example.Project2.Service;


import com.example.Project2.Service.Regex.Regex;
import com.example.Project2.Service.ResponseHandler.DeleteHandler;
import com.example.Project2.Service.ResponseHandler.ResponseHandler;
import com.example.Project2.infra.ProductRepository;
import com.example.Project2.infra.entities.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    ResponseHandler responseHandler;
    DeleteHandler deleteHandler;

    public ResponseEntity save(ProductDB productDB) {
        String desc, type;
        Integer amount;
        Float price;
        Boolean verifyDesc;
        Boolean verifyType;
        amount=  productDB.getAmount();
        price = productDB.getPrice();
        desc = productDB.getDescription();
        type = productDB.getType();
        Regex regex = new Regex();
        verifyDesc = regex.description(desc);
        verifyType = regex.description(type);
       //desc.isBlank()||desc.isEmpty()
        // StringUtils

        if( verifyDesc|| verifyType || price <= 0 || amount <= 0 ){
            responseHandler = new ResponseHandler("400", "Valores invalidos", HttpStatus.BAD_REQUEST, new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHandler);
        }
        else {
            productRepository.save(productDB);
            responseHandler = new ResponseHandler("201", "Cadastrado", HttpStatus.CREATED, new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseHandler);
        }
    }

    public List<ProductDB> listProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDB> listFilterId(Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity delete(Long id) {
        ProductDB productDB =  productRepository.getById(id);
        deleteHandler = new DeleteHandler("202", "Deletado", HttpStatus.ACCEPTED, new Date(), productDB.getDescription(), productDB.getType(), productDB.getPrice(), productDB.getAmount());
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteHandler);
    }

    public ProductDB editById(Long id, ProductDB productDB) {

        return productRepository.save(productDB);
    }

    public List<ProductDB> filterType(String type) {
        return productRepository.findByType(type);
    }

    public ProductDB updatePrice(Long id, Float price) {

        ProductDB productDB = productRepository.findByCodigo(id);
        productDB.setPrice(price);
        return productRepository.save(productDB);
    }
    public ProductDB updateAmount(Long id, Integer amount) {
        ProductDB productDB = productRepository.findByCodigo(id);
        productDB.setAmount(amount);
        return productRepository.save(productDB);
    }
    public Page<ProductDB> paginacao(Pageable pageable){
        return productRepository.findAll(pageable);
    }

}

