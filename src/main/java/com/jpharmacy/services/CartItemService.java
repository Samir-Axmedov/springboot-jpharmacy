package com.jpharmacy.services;

import com.jpharmacy.models.Cart;
import com.jpharmacy.models.CartItem;

public interface CartItemService {

    void addCartItem(CartItem cartItem);

    void removeCartItem(CartItem cartItem);

    void removeAllCartItems(Cart cart);

    CartItem getCartItemByProductId(Long id);


}
