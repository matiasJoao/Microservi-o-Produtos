package com.example.Project2.Service;


import com.example.Project2.Service.Regex.Regex;
import com.example.Project2.Service.ResponseHandler.DeleteHandler;
import com.example.Project2.Service.ResponseHandler.ResponseHandler;
import com.example.Project2.Service.ResponseHandler.ResponseStatusErrorException;
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

    public ProductDB save(ProductDB productDB) {
        String desc, type;
        Integer amount;
        Float price;
      // Boolean verifyDesc;
      //  Boolean verifyType;
        amount=  productDB.getAmount();
        price = productDB.getPrice();
        desc = productDB.getDescription();
        type = productDB.getType();
      //  Regex regex = new Regex();
      //  verifyDesc = regex.description(desc);
       // verifyType = regex.description(type);
       //desc.isBlank()||desc.isEmpty()
        // StringUtils
      var test = productRepository.findAll().stream().filter(p ->p.getDescription().equalsIgnoreCase(productDB.getDescription())).findFirst();

      if      (desc.isBlank() || type.isBlank() || price <= 0 || amount <= 0)
            {
                throw new ResponseStatusErrorException("empty args");
            }
      else if (test.isPresent())
             {
               return   updateAmount(test.get().getCodigo(), test.get().getAmount()+productDB.getAmount());
            }
      else
            {
                  productRepository.save(productDB);
                  //responseHandler = new ResponseHandler("201", "Cadastrado", HttpStatus.CREATED, new Date());
                  return productDB;
            }


    }

    public List<ProductDB> listProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductDB> listFilterId(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDB editById(Long id, ProductDB productDB) {
        return productRepository.save(productDB);
    }

    public List<ProductDB> filterType(String type, Pageable pageable) {
        return productRepository.findByType(type, pageable);
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

    public Page<ProductDB> findDesc (String description, Pageable pageable){
        Page<ProductDB> desc = productRepository.findDescription(description, pageable);
        return desc;
    }
   /* public Page<ProductDB> findPrice(Float price, Pageable pageable){
        Page<ProductDB> pri  = productRepository.findPrice(price, pageable);
        return  pri;
    }
    */
}



