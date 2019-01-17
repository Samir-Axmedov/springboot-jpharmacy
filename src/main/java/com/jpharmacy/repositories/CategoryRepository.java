package com.jpharmacy.repositories;

import com.jpharmacy.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Page<Product> findByCategoryName(String phrase, Pageable pageable);
    Category findByCategoryName(final String categoryName);
}
