package com.FreeShipForShop.Ecommerce.DAO;

import com.FreeShipForShop.Ecommerce.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category") //to change the name and
//path
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

}
