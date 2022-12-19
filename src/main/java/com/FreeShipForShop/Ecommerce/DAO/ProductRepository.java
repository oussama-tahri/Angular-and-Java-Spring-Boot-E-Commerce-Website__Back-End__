package com.FreeShipForShop.Ecommerce.DAO;

import com.FreeShipForShop.Ecommerce.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product,Long> {
                                                        //entity type is Product and primary key is long

 Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

 // "Containing"  in Java means  "LIKE"  in SQL
 Page<Product> findByNameContaining(@Param("name") String name, Pageable page);

}
