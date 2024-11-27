package com.art.service;

import com.art.modal.Cart;
import com.art.modal.CartItem;
import com.art.modal.Product;
import com.art.modal.User;

public interface CartService {

    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
}

