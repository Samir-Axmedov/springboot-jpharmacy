package com.jpharmacy.controllers;

import com.jpharmacy.services.CartService;
import com.jpharmacy.models.Cart;
import com.jpharmacy.models.Product;
import com.jpharmacy.services.ProductService;
import com.jpharmacy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;



    @GetMapping("/add/{ItemId}")
    public String addItemToCartRequest(@PathVariable Long ItemId, RedirectAttributes ra, HttpSession session){
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();


        cartService.addItemToCart(ItemId, currentPrincipalName, session);
        session.removeAttribute("cartlist");
        session.setAttribute("cartlist", cartService.getUserCarts(currentPrincipalName));
        //ra.addFlashAttribute("message", "Dodano przedmiot do koszyka!");
        return "redirect:/products";
    }


    @GetMapping("/list")
    public String showUserCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> userCart = cartService.getUserCarts(username);
        model.addAttribute("userCart", userCart);
        model.addAttribute("itemsPrice", cartService.getCartsPrice(username));
        return "cart/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteItemFromCart(@PathVariable Long id, RedirectAttributes ra, HttpSession session){
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        if( cartService.isCartExists(id) ){
            Product product = productService.getProduct(cartService.getCartById(id).getProduct().getId());
            product.setUnitInStock(product.getUnitInStock()+1);
            cartService.deleteCart(id);
            ra.addFlashAttribute("message", "<h1>Usunięto przedmiot z koszyka!</h1>");
        }else{
            ra.addFlashAttribute("message", "<h1>Nie posiadasz tego przedmiotu w koszyku!</h1>");
        }
        session.setAttribute("cartlist", cartService.getUserCarts(currentPrincipalName));
        return "redirect:/cart/list";
    }


}
