package com.jpharmacy.controllers.admin;

import com.jpharmacy.services.ProductService;
import com.jpharmacy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHome {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping
    public String admin(){
        return "admin";
    }



    

}
