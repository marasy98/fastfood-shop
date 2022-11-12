package com.example.fast_food.controller;

import com.example.fast_food.entities.CartItem;
import com.example.fast_food.entities.Product;
import com.example.fast_food.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addCartItem")
    public void addCartItem(@RequestParam long id,@RequestParam int quantity){
        CartItem cartItem=new CartItem();
        Product product=new Product();
        product.setProductId(id);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemService.saveCartItem(cartItem);
    }
}
