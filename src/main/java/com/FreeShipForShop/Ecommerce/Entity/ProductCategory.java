package com.FreeShipForShop.Ecommerce.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product_category")
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    //cascade type all means wherever we save ProductCategory in our DB, products will be saved also
    //OneToMany means One ProductCategory can have many Products
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products; //contain maximum 1 element "null" & contain no duplicate elements
}

