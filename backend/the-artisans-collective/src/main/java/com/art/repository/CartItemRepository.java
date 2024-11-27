package com.art.repository;

import com.art.modal.Cart;
import com.art.modal.CartItem;
import com.art.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
