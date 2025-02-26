package com.example.springbootexam.controller;

import com.example.springbootexam.model.Order;
import com.example.springbootexam.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.insert(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getPromoById(@PathVariable int id) {
        return Optional.ofNullable(orderService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{date}")
    public ResponseEntity<List<Order>> searchProductByDate(@PathVariable Date date) {
        return Optional.ofNullable(orderService.searchProduct(date))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateQuantity(@PathVariable int id, @RequestBody int newQuantity) {
        return ResponseEntity.ok(orderService.updateQuantity(id, newQuantity));
    }
}


