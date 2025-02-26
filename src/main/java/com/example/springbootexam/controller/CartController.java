package com.example.springbootexam.controller;

import com.example.springbootexam.model.Cart;
import com.example.springbootexam.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    @Autowired
    private final CartService cartService;


    @PostMapping
    public ResponseEntity<Cart> insert(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.insert(cart));
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable int id) {
        return ResponseEntity.ok(cartService.getById(id));
    }


    @GetMapping("/type/{type}")
    public ResponseEntity<List<Cart>> cartByType(@PathVariable String type) {
        return ResponseEntity.ok(cartService.searchCart(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartByType(@PathVariable int id) {
        cartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/type/{type}")
    public ResponseEntity<Cart> updateTypeCart(@PathVariable String newType, @RequestParam int id) {
        return ResponseEntity.ok(cartService.updateCartType(newType,id));
    }

}
