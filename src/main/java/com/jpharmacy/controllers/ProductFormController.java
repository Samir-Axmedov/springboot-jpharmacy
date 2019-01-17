package com.jpharmacy.controllers;

import com.jpharmacy.models.Category;
import com.jpharmacy.models.Product;
import com.jpharmacy.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={"categoryList", "product"})
@Log4j2
public class ProductFormController {
    @Autowired
	private ProductService productService;
    private Path path;
    @Autowired
    private ServletContext servletContext;

	public ProductFormController(ProductService productService)
	{
		this.productService = productService;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/productForm", method= RequestMethod.GET)
	public String showForm(Model model, Optional<Long> id){

		model.addAttribute("product",
				id.isPresent()?
				productService.getProduct(id.get()):
				new Product());

		return "productForm";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/productForm", method= RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("product") Product product, BindingResult errors,  HttpServletRequest request){

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\resources\\images\\"+product.getProductName()+".png");
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed.", e);
            }
        }

		if(errors.hasErrors()){
			return "productForm";
		}

		productService.saveProduct(product);

		return "redirect:products";
	}	

	@ModelAttribute("categoryList")
	public List<Category> loadCategories(){
		List<Category> categories = productService.getAllCategories();
		log.info("Ładowanie listy "+categories.size()+" kategorii ");
		return categories;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {//Rejestracja edytora właściwości

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
		binder.registerCustomEditor(Date.class, "productionDate", dateEditor);

		DecimalFormat numberFormat = new DecimalFormat("#0.00");
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
		binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));
		binder.setDisallowedFields("createdDate");

	}

}








