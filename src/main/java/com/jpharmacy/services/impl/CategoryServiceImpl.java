package com.jpharmacy.services.impl;

import com.jpharmacy.repositories.ProductRepository;
import com.jpharmacy.exceptions.ProductNotFoundException;
import com.jpharmacy.models.Category;
import com.jpharmacy.models.Product;
import com.jpharmacy.repositories.CategoryRepository;
import com.jpharmacy.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        Page page = categoryRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Product> getAllProductsByCategory(Long id, Pageable pageable) {
        Page page = productRepository.findAllProductsByCategory(id,pageable);
        return page;
    }

    @Transactional
    @Override
    public Category getCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(()->new ProductNotFoundException(id));
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public boolean isUniqueCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName) == null;
    }
}
