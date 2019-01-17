package com.jpharmacy.services.impl;

import com.jpharmacy.models.Cart;
import com.jpharmacy.models.Category;
import com.jpharmacy.models.TheOrder;
import com.jpharmacy.models.User;
import com.jpharmacy.repositories.CartRepository;
import com.jpharmacy.repositories.OrderRepostiory;
import com.jpharmacy.repositories.UserRepository;
import com.jpharmacy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepostiory orderRepostiory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void saveUserOrder(List<Cart> carts) {
        carts.forEach(cart->{
            User user = userRepository.findByUsername(cart.getUsername());
            String item_name = cart.getProduct().getProductName();
            Float item_price = cart.getProduct().getProductPrice();
            String item_description = cart.getProduct().getProductDescription();
            Category category = cart.getProduct().getCategory();
            orderRepostiory.save(new TheOrder(user, item_name, item_price, item_description, category, new Date()));
            cartRepository.deleteById(cart.getId());
        });
    }

    @Override
    public List<TheOrder> getUserOrders(String username) {
        return orderRepostiory.getUserOrders(username);
    }

    @Override
    public Float getOrdersPrice(List<TheOrder> orders) {
        AtomicReference<Float> price= new AtomicReference<>(0f);
        orders.forEach(order-> price.updateAndGet(v -> v + order.getItem_price()));
        return price.get();
    }

}
