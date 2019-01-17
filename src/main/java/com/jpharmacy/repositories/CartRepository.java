package com.jpharmacy.repositories;

import com.jpharmacy.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
    @Query("SELECT v "+
            "FROM Cart v WHERE " +
            "(" + "upper(v.username) LIKE upper(:username)" + ") "
    )
    List<Cart> getUserCart(@Param("username") String param);

}
