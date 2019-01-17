package com.jpharmacy.repositories;

import com.jpharmacy.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.category.Id = :Id")
    Page<Product> findAllProductsByCategory(@Param("Id") Long Id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(p.productName) LIKE upper(:phrase) OR " +
            "upper(p.category.categoryName) LIKE upper(:phrase)" +
            ") " +
            "AND " +
            "(:min is null OR :min <= p.productPrice) " +
            "AND (:max is null OR :max >= p.productPrice)" +
            "OR (p.category.Id = :cId)")
    Page<Product> findAllProductsUsingFilter(@Param("phrase") String p, @Param("min") Float priceMin,
                                             @Param("max") Float priceMax, @Param("cId") Long cId, Pageable pageable);

}