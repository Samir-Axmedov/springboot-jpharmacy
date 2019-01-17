package com.jpharmacy.repositories;

import com.jpharmacy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByUserEmail(String userEmail);
    User findByUserPhone(String userPhone);
    List<User> findAllByUserFirstName(String username);
    @Modifying
    @Query("DELETE FROM User u WHERE u.id > 1 AND u.id = :Id")
    void deleteUserById(@Param("Id") Long id);
}
