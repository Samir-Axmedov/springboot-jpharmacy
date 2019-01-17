package com.jpharmacy.services;

import com.jpharmacy.models.Product;
import com.jpharmacy.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Category> getAllCategories(Pageable pageable);

    Page<Product> getAllProductsByCategory(Long id, Pageable pageable);

    Category getCategory(Long id);

    void deleteCategory(Long id);

    void saveCategory(Category category);

    boolean isUniqueCategory(String categoryName);
}
