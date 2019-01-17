package com.jpharmacy.services;


import com.jpharmacy.models.Cart;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    Cart getCartById (Long Id);
    List<Cart> getUserCarts(String username);
    void addItemToCart(Long ItemId, String username, HttpSession session);
    void deleteCart(long cart_id);
    Float getCartPrice(long cart_id);
    Float getCartsPrice(String username);
    List<Cart> getCartsConsistsItemByItemId(long item_id);
    boolean isCartExists(long cart_id);
}
