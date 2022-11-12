package com.example.fast_food.service.impl;

import com.example.fast_food.entities.CartItem;
import com.example.fast_food.repositories.CartItemRepository;
import com.example.fast_food.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}
