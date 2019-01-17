package com.jpharmacy.controllers.admin;

import com.jpharmacy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showCategories(Model model, Pageable pageable){
        model.addAttribute("userListPage",userService.getAllUsers(pageable));
        return "users";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/users", params={"did"})
    public String deleteCategory(long did, HttpServletRequest request){
        userService.delete(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:users%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }

}
