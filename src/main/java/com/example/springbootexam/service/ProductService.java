package com.example.springbootexam.service;

import com.example.springbootexam.model.Product;
import com.example.springbootexam.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductService {
    private final ProductDAO productDAO;
    @Autowired
    public ProductService(ProductDAO productDAO){
        this.productDAO=productDAO;
    }
    public void stockProduct(){productDAO.stockProduct();}
    public List<Product> findAll(){return productDAO.findAll();}
    public Product updateCategory(String newCategory, int id){return productDAO.updateCategory(newCategory,id);}
    public List<Product> searchProduct(String product){return productDAO.searchProduct(product);}
    public void deleteById(int productId){productDAO.deleteById(productId);}
    public Product insert(Product product){return productDAO.insert(product);}
    public Product getById(int id){return productDAO.getById(id);}
}
