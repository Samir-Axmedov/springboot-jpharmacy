package com.jpharmacy.services.impl;

import com.jpharmacy.models.Cart;
import com.jpharmacy.repositories.ProductRepository;
import com.jpharmacy.services.CartService;
import com.jpharmacy.models.Product;
import com.jpharmacy.models.User;
import com.jpharmacy.repositories.CartRepository;
import com.jpharmacy.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart getCartById(Long Id) {
        return cartRepository.getOne(Id);
    }

    @Override
    public List<Cart> getUserCarts(String username) {
        return cartRepository.getUserCart(username);
    }

    @Override
    public void addItemToCart(Long ItemId, String username, HttpSession session) {
        Product product = productRepository.getOne(ItemId);
        if( product.getUnitInStock()>0){
            product.setUnitInStock(product.getUnitInStock()-1);
            User user = userRepository.findByUsername(username);
            user.setUserAddress(user.getUserAddress());
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setUsername(username);
            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteCart(long cart_id) {
        cartRepository.deleteById(cart_id);
    }

    @Override
    public Float getCartPrice(long cart_id) {
        return cartRepository.getOne(cart_id).getProduct().getProductPrice();
    }

    @Override
    public Float getCartsPrice(String username) {
        AtomicReference<Float> price = new AtomicReference<>((float) 0.00);
        getUserCarts(username).forEach(cart-> price.updateAndGet(v -> new Float((float) (v + cart.getProduct().getProductPrice()))));
        return price.get();
    }

    @Override
    public List<Cart> getCartsConsistsItemByItemId(long item_id) {

        List<Cart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(cart->{
            if(cart.getProduct().getId()== item_id){
                carts.add(cart);
            }
        });
        return carts;
    }

    @Override
    public boolean isCartExists(long cart_id) {

        return cartRepository.existsById(cart_id);
    }
}
