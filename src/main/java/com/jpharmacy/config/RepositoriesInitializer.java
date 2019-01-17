package com.jpharmacy.config;

import com.jpharmacy.models.Category;
import com.jpharmacy.models.Product;
import com.jpharmacy.models.Role;
import com.jpharmacy.models.User;
import com.jpharmacy.repositories.CategoryRepository;
import com.jpharmacy.repositories.ProductRepository;
import com.jpharmacy.repositories.RoleRepository;
import com.jpharmacy.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Bean
    InitializingBean init() {

        return () -> {

            if(categoryRepository.findAll().isEmpty() == true){
                Category category1 = new Category("Przeziebienie");
                Category category2 = new Category("Grypa");
                categoryRepository.save(category1);
                categoryRepository.save(category2);


                Product product1 = new Product();
                product1.setProductName("Rutinoscorbin");
                product1.setProductDescription("Super lek na przeziebienie");
                product1.setProductManufacturer("SuperPharm");
                product1.setProductPrice(12f);
                product1.setUnitInStock(3);
                product1.setProductionDate(new Date(2017, 8, 16));
                product1.setExpirationDate(new Date(2020, 8, 16));
                product1.setCategory(category1);
                productRepository.save(product1);
            }


            if(userRepository.findAll().isEmpty() == true){
                Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));
                User admin = new User("admin", true);
                admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                admin.setPassword(passwordEncoder.encode("admin"));
                Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                User userek = new User("user", true);
                userek.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                userek.setPassword(passwordEncoder.encode("user"));
                userRepository.save(admin);
                userRepository.save(userek);
            }

        };
    }

}
