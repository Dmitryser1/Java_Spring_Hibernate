package com.example.shop.service;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.model.Cart;
import com.example.shop.model.Product;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public Cart getCart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + id));
    }


    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = getCart(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }


    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = getCart(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
        if (cart.getProducts().remove(product)) {
            return cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("Product with id " + productId + " not found in cart " + cartId);
        }
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }
}
