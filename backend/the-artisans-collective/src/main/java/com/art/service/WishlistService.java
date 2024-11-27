package com.art.service;

import com.art.modal.Product;
import com.art.modal.User;
import com.art.modal.Wishlist;

public interface WishlistService {
    Wishlist createWishList(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
