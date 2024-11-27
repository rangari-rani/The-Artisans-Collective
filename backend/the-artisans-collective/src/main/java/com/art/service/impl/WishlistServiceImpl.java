package com.art.service.impl;

import com.art.modal.Product;
import com.art.modal.User;
import com.art.modal.Wishlist;
import com.art.repository.WishlistRepository;
import com.art.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    @Override
    public Wishlist createWishList(User user) {
        Wishlist wishlist=new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);

    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist=wishlistRepository.findByUserId(user.getId());
        if(wishlist==null){
            wishlist=createWishList(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist=getWishlistByUserId(user);

        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }else wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }
}
