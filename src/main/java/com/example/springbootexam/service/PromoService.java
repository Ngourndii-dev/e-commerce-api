package com.example.springbootexam.service;

import com.example.springbootexam.model.Promo;
import com.example.springbootexam.repository.PromoDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class PromoService {
    @Autowired
    private final PromoDAO promoDAO;

    public Promo insert(Promo promo){return promoDAO.insert(promo);}
    public Promo getById(int id){return promoDAO.getById(id);}
    public Promo updatePromo(Date newExpiration, int id){return promoDAO.updatePromo(newExpiration, id);}
    public void deleteById(int promoId){promoDAO.deleteById(promoId);}
    public List<Promo> findAll(){return promoDAO.findAll();}
    public List<Promo> searchPromo(String category){return promoDAO.searchPromo(category);}
}
