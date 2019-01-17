package com.jpharmacy.services;

import com.jpharmacy.controllers.commands.ProductFilter;
import com.jpharmacy.models.Product;
import com.jpharmacy.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Category> getAllCategories();

    Page<Product> getAllProducts(ProductFilter filter, Pageable pageable);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    void saveProduct(Product vehicle);
}
