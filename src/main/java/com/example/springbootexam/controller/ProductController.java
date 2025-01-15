
// ProductController.java
package com.example.springbootexam.controller;

import com.example.springbootexam.model.Product;
import com.example.springbootexam.model.Promo;
import com.example.springbootexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.insert(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{productName}")
    public ResponseEntity<List<Product>> productByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.searchProduct(productName));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getPromoById(@PathVariable int id) {
        return Optional.ofNullable(productService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/{category}")
    public ResponseEntity<Product> updateCategory(@PathVariable String category, @RequestBody int id) {
        return ResponseEntity.ok(productService.updateCategory(category, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/stock")
    public ResponseEntity<Void> stockProduct() {
        productService.stockProduct();
        return ResponseEntity.noContent().build();
    }
}
