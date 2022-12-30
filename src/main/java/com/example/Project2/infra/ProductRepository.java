package com.example.Project2.infra;

import com.example.Project2.infra.entities.ProductDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductDB,Long> {
   List<ProductDB> findByType(String type);
   ProductDB findByCodigo(Long codigo);
}
