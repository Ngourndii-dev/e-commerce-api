package com.example.springbootexam.repository;

import com.example.springbootexam.model.Client;
import com.example.springbootexam.model.ECommerce;
import com.example.springbootexam.model.Product;
import com.example.springbootexam.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class ECommerceDAO{
    @Autowired
    private Connection connection;


    public List<Product> listProduct() {
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("select * from product");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                System.out.println(result.getInt("productid"));
                System.out.println(result.getString("productname"));
                System.out.println(result.getFloat("price"));
                System.out.println(result.getInt("stockquantity"));
                System.out.println(result.getString("category"));
                System.out.println(result.getString("categorydesc"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ECommerce listCategory() {
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("select category from product");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                System.out.print(result.getInt("id"));
                System.out.print(result.getString("username"));
                System.out.print(result.getString("email"));
                System.out.print(result.getString("work"));
                System.out.print(result.getString("password"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Client> listClient() {
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("select * from client");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                System.out.print(result.getString("clientname"));
                System.out.print(" has ");
                System.out.print(result.getString("phonenumber"));
                System.out.print(" Number client : ");
                System.out.println(result.getInt("clientid"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> listBoss() {
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("select * from users");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                System.out.print(result.getInt("id"));
                System.out.print(result.getString("username"));
                System.out.print(result.getString("email"));
                System.out.print(result.getString("work"));
                System.out.print(result.getString("password"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
