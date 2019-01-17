package com.jpharmacy.repositories.criteria;

import com.jpharmacy.models.Category_;
import com.jpharmacy.models.Product;
import com.jpharmacy.models.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecifications {
    public static Specification<Product> findByPhrase(final String phrase) {
        return (root, query, cb) -> {

            if(StringUtils.isEmpty(phrase) == false){
                String phraseLike = "%"+phrase.toUpperCase() +"%";
                return cb.or(
                        cb.or(
                                cb.like(cb.upper(root.get(Product_.productName)), phraseLike)
                                //cb.like(cb.upper(root.get(Vehicle_.model)), phraseLike)
                        ),
                        cb.like(cb.upper(root.get(Product_.category).get(Category_.categoryName)), phraseLike)
                );
            }
            return null;
        };
    }

    public static Specification<Product> findByPriceRange(Float minPrice, Float maxPrice) {
        return (root, query, cb) -> {
            if(minPrice != null && maxPrice != null){
                return cb.between(root.get(Product_.productPrice), minPrice, maxPrice);
            }else if(minPrice != null){
                return cb.greaterThanOrEqualTo(root.get(Product_.productPrice), minPrice);
            }else if(maxPrice != null) {
                return cb.lessThanOrEqualTo(root.get(Product_.productPrice), maxPrice);
            }
            return null;
        };
    }

}
