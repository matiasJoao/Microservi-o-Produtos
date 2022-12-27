package com.example.Project2.infra;

import com.example.Project2.infra.entities.ProductDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDB,Long> {
}
