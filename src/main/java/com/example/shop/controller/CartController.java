package com.example.shop.controller;

import com.example.shop.model.Cart;
import com.example.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.getCart(id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart newCart = cartService.createCart();
        return ResponseEntity.ok(newCart);
    }
}
