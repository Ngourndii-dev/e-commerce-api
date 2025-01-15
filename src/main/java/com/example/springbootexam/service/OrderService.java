package com.example.springbootexam.service;

import com.example.springbootexam.model.Order;
import com.example.springbootexam.repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    public final OrderDAO orderDAO;
    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public Order insert(Order order){return orderDAO.insert(order);}
    public List<Order> findAll(){return orderDAO.findAll();}
    public Order getById(int id){return orderDAO.getById(id);}
    public void deleteById(int id){orderDAO.deleteById(id);}
    public Order updateQuantity(int id, int newQuantity){return orderDAO.updateQuantity(id, newQuantity);}
    public List<Order> searchProduct(Date date){return orderDAO.searchProduct(date);}
}
