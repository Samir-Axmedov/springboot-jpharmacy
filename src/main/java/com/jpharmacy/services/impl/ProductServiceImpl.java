package com.jpharmacy.services.impl;

import com.jpharmacy.controllers.commands.ProductFilter;
import com.jpharmacy.exceptions.ProductNotFoundException;
import com.jpharmacy.models.Product;
import com.jpharmacy.repositories.ProductRepository;
import com.jpharmacy.services.ProductService;
import com.jpharmacy.models.Category;
import com.jpharmacy.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(ProductFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = productRepository.findAll(pageable);
        }else{
            page = productRepository.findAllProductsUsingFilter(search.getPhraseLIKE(), search.getMinPrice(),
                    search.getMaxPrice(), search.getCategory(), pageable);
        }
        return page;
    }

    //@Transactional
    @Override
    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(()->new ProductNotFoundException(id));
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepository.existsById(id) == true){
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
