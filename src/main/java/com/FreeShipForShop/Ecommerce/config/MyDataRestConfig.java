package com.FreeShipForShop.Ecommerce.config;


import com.FreeShipForShop.Ecommerce.Entity.Product;
import com.FreeShipForShop.Ecommerce.Entity.ProductCategory;
import org.hibernate.resource.beans.container.spi.BeanLifecycleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;
    // we will auto-wire JPA EntityManager
    @Autowired
    public MyDataRestConfig(EntityManager TheEntityManager){

        entityManager = TheEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        //we will use only read for our code
        //So we will disable HTTP methods for products ( POST - PUT - DELETE )
        //we'll only use GET to read a collection and single item
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(Product.class) //to configure it on our Product.java class
                //we use java lambda syntax for a given product item
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                //we will do the same for collection
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class) //to configure it on our Product.java class
                //we use java lambda synetx for a given productCategory item
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                //we will do the same for collection
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // call an internal helper method
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
//---------------------------------------------------------------------------------------------------------------
                        //this is how to expose ids for entities using Spring Data Rest
//---------------------------------------------------------------------------------------------------------------

        //expose entity Ids

        // get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for entities
        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        // expose the entity ids for the array of entities/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
