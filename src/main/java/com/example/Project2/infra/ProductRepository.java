package com.example.Project2.infra;

import com.example.Project2.infra.entities.ProductDB;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductDB,Long> {
   List<ProductDB> findByType(String type, Pageable pageable);
   ProductDB findByCodigo(Long codigo);
   @Query(value = "select d from ProductDB d where d.description like ?1%")
   Page<ProductDB> findDescription (String description, Pageable pageable );

 /*  @Query(value = "select p from ProductDB p where p.price like ?1%")
      Page<ProductDB> findPrice (Float price, Pageable pageable );

  */
 /* @Query(value = "select n from ProductDB n where n.description = ?1")
    Optional<ProductDB> verifyEquals(String description);

  */
}
