package com.example.springbootexam.service;
import com.example.springbootexam.model.Cart;
import com.example.springbootexam.repository.CartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartDAO cartDAO;
    @Autowired
    public CartService(CartDAO cartDAO){
        this.cartDAO=cartDAO;
    }

    public Cart insert(Cart cart){
        return cartDAO.insert(cart);
    }
    public List<Cart> findAll(){return cartDAO.findAll();}
    public Cart getById(int id){return cartDAO.getById(id);}
    public void deleteById(int id){cartDAO.deleteById(id);}
    public Cart updateCartType(String newType, int id){return cartDAO.updateCartType(newType,id);}
    public List<Cart> searchCart(String category){return cartDAO.searchCart(category);}
}
