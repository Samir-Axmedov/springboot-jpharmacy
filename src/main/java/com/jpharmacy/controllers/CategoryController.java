package com.jpharmacy.controllers;

import com.jpharmacy.models.Category;
import com.jpharmacy.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.Optional;

@SessionAttributes("searchCommand")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value="/categoryForm", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("category",
                id.isPresent()?
                        categoryService.getCategory(id.get()):
                        new Category());

        return "categoryForm";
    }
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value="/categoryForm", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("category") Category c, BindingResult errors){

        if(errors.hasErrors()){
            return "categoryForm";
        }
        categoryService.saveCategory(c);
        return "redirect:categories";//po udanym dodaniu/edycji przekierowujemy na listę
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String showCategories(Model model, Pageable pageable){
        model.addAttribute("categoryListPage",categoryService.getAllCategories(pageable));
        return "categoryList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/categories", params={"did"})
    public String deleteCategory(long did, HttpServletRequest request){
        categoryService.deleteCategory(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:categories%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "minPrice", priceEditor);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }


}
