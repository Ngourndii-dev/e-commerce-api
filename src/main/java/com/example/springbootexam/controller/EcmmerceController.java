package com.example.springbootexam.controller;

import com.example.springbootexam.model.*;
import com.example.springbootexam.service.ECommerceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce")
@AllArgsConstructor
public class EcmmerceController {
    @Autowired
    private final ECommerceService eCommerceService;


    @PostMapping("/client")
    public ResponseEntity<ECommerce> addClient(@RequestBody Client client) {
        return ResponseEntity.ok((ECommerce) eCommerceService.listClient(client));
    }

    @PostMapping("/user")
    public ResponseEntity<ECommerce> addUser(@RequestBody User user) {
        return ResponseEntity.ok((ECommerce) eCommerceService.UserList(user));
    }

    @PostMapping("/product")
    public ResponseEntity<ECommerce> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok((ECommerce) eCommerceService.productList(product));
    }

    @GetMapping("/{category}")
    public ResponseEntity<ECommerce> getCategory(@PathVariable String category) {
        return ResponseEntity.ok(eCommerceService.getAllCategory(category));
    }
}

