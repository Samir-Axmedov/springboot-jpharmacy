package com.jpharmacy.services;

import com.jpharmacy.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);
    boolean isUniqueLogin(String login);
    boolean isUniqueEmail(String Email);
    boolean isValidPhone(String userPhone);
    Page<User> getAllUsers(Pageable pageable);
    void delete(Long id);
    User getUser(String username);
}
