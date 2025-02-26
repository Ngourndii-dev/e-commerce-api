package com.example.springbootexam.controller;

import com.example.springbootexam.model.Client;
import com.example.springbootexam.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllPromos() {
        return ResponseEntity.ok(clientService.findAll());
    }
    @GetMapping("/{client}")
    public ResponseEntity<List<Client>> getCartByPhonenumber(@PathVariable String client) {
        return Optional.ofNullable(clientService.searchClient(client))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Client> insertUser(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.insert(client));
    }
    @PutMapping("/name")
    public ResponseEntity<Client> updateName(@RequestParam String name, @RequestParam int id) {
        return ResponseEntity.ok(clientService.updateClientName(name, id));
    }

    @PutMapping("/phoneNumber")
    public ResponseEntity<Client> updatePhoneNumber(@RequestParam String number, @RequestParam int id) {
        return ResponseEntity.ok(clientService.updatePhoneNumber(number, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
        }
}

