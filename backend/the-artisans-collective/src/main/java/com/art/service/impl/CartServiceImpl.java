package com.art.service.impl;

import com.art.modal.Cart;
import com.art.modal.CartItem;
import com.art.modal.Product;
import com.art.modal.User;
import com.art.repository.CartItemRepository;
import com.art.repository.CartRepository;
import com.art.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart=findUserCart(user);

        CartItem isPresent=cartItemRepository.findByCartAndProductAndSize(cart,product,size);


        if(isPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice=quantity* product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

//    @Override
//    public Cart findUserCart(User user) {
//        Cart cart=cartRepository.findByUserId(user.getId());
//        int totalPrice=0;
//        int totalDiscountedPrice=0;
//        int totalItem=0;
//
//        for(CartItem cartItem: cart.getCartItems()){
//            totalPrice+=cartItem.getMrpPrice();
//            totalDiscountedPrice+=cartItem.getSellingPrice();
//            totalItem+=cartItem.getQuantity();
//        }
//        cart.setTotalMrpPrice(totalPrice);
//        cart.setTotalItem(totalItem);
//        cart.setTotalSellingPrice(totalDiscountedPrice);
//        cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
//
//        return cart;
//    }
@Override
public Cart findUserCart(User user) {
    Cart cart = cartRepository.findByUserId(user.getId());
    int totalPrice = 0;
    int totalDiscountedPrice = 0;
    int totalItem = 0;

    for (CartItem cartItem : cart.getCartItems()) {
        // Check for null values before accessing mrpPrice and sellingPrice
        Integer mrpPrice = cartItem.getMrpPrice();
        Integer sellingPrice = cartItem.getSellingPrice();

        // Use 0 if mrpPrice or sellingPrice is null
        totalPrice += (mrpPrice != null) ? mrpPrice : 0;
        totalDiscountedPrice += (sellingPrice != null) ? sellingPrice : 0;
        totalItem += cartItem.getQuantity();
    }

    cart.setTotalMrpPrice(totalPrice);
    cart.setTotalItem(totalItem);
    cart.setTotalSellingPrice(totalDiscountedPrice);
    cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));

    return cart;
}


    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {

        if (mrpPrice <= 0) {
           return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int) discountPercentage;
    }
}
