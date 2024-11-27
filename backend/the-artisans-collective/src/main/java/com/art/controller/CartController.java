package com.art.controller;

import com.art.modal.Cart;
import com.art.modal.CartItem;
import com.art.modal.Product;
import com.art.modal.User;
import com.art.request.AddItemRequest;
import com.art.response.ApiResponse;
import com.art.service.CartItemService;
import com.art.service.CartService;
import com.art.service.ProductService;
import com.art.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;


//    @GetMapping
//    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
//
//        Cart cart = cartService.findUserCart(user);
//    System.out.println(cart);
//        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
//    }

    @GetMapping

    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String authHeader) throws Exception {
        System.out.println("Received Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.err.println("Invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String jwt = authHeader.substring(7); // Remove "Bearer " prefix
        System.out.println("Extracted JWT: " + jwt);

        try {
            User user = userService.findUserByJwtToken(jwt);
            if (user == null) {
                System.err.println("User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Cart cart = cartService.findUserCart(user);
            System.out.println("Cart fetched successfully: " + cart);
            return ResponseEntity.ok(cart);

        } catch (Exception e) {
            System.err.println("Error processing JWT: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(
                user,
                product,
                req.getSize(),
                req.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable long cartItemId, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item remove from cart ");

        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if (cartItem.getQuantity() > 0) {
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        }
        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }
}
