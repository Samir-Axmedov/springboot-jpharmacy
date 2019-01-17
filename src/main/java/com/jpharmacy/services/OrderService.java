package com.jpharmacy.services;

import com.jpharmacy.models.Cart;
import com.jpharmacy.models.TheOrder;

import java.util.List;

public interface OrderService {
    void saveUserOrder(List<Cart> carts);
    List<TheOrder> getUserOrders(String username);
    Float getOrdersPrice(List<TheOrder> orders);
}
