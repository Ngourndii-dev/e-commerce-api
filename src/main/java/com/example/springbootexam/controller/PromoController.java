package com.example.springbootexam.controller;

import com.example.springbootexam.model.Promo;
import com.example.springbootexam.service.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PromoController {
@Autowired
    private final PromoService promoService;

    @PostMapping
    public ResponseEntity<Promo> insertPromo(@RequestBody Promo promo) {
        return ResponseEntity.ok(promoService.insert(promo));
    }

    @GetMapping
    public ResponseEntity<List<Promo>> getAllPromos() {
        return ResponseEntity.ok(promoService.findAll());
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<Promo>> searchByCategory(@PathVariable String category) {
        return ResponseEntity.ok(promoService.searchPromo(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promo> getPromoById(@PathVariable int id) {
        return Optional.ofNullable(promoService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromo(@PathVariable int id) {
        promoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Promo> updateExpirationDate(@RequestBody Promo promo) {
        return ResponseEntity.ok(promoService.updatePromo(promo.getExpirationDate(), promo.getId()));
    }
}
