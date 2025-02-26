package com.example.springbootexam.repository;

import com.example.springbootexam.model.Cart;
import com.example.springbootexam.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CartDAO implements CrudOperation<Cart> {
    @Autowired
    private final Connection connection;
    @Override
    public Cart insert(Cart cart) {
        String sql = "INSERT INTO cart (reference, type_cart, id_client) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, cart.getReference());
            statement.setString(2, cart.getTypeCart());
            statement.setInt(3, cart.getClient().getId());
            statement.executeUpdate();
            System.out.println("Cart inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error during insertion: " + e.getMessage());
        }
        return cart;
    }

    @Override
    public List<Cart> findAll() {
        String query = "SELECT * FROM cart";
        List<Cart> carts = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                Cart cart = mapResultSetToCart(result);
                carts.add(cart);
            }
        } catch (SQLException e) {
            System.err.println("Error during retrieval: " + e.getMessage());
        }
        return carts;
    }

    @Override
    public Cart getById(int id) {
        String query = "SELECT * FROM cart WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return mapResultSetToCart(rs);
        } catch (SQLException e) {
            System.err.println("Error during retrieval: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM cart WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Deletion successful.");
            }
        } catch (SQLException e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }
    }

    public Cart updateCartType(String newType, int id) {
        String sql = "UPDATE cart SET type_cart = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newType);
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Update successful.");
            }
        } catch (SQLException e) {
            System.err.println("Error during update: " + e.getMessage());
        }
        return null;
    }

    private Cart mapResultSetToCart(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getInt("id"));
        cart.setReference(rs.getInt("reference"));
        cart.setTypeCart(rs.getString("type_cart"));
        Client client = new Client();
        client.setId(rs.getInt("id_client"));
        cart.setClient(client);
        return cart;
    }

    public List<Cart> searchCart(String category){
        List<Cart> cartList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM cart WHERE type_cart ILIKE '%s'",category);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                cartList.add(new Cart(
                                result.getInt("id"),
                                result.getInt("reference"),
                                result.getString("description"),
                                result.getObject("id_client", Client.class)
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return cartList;
    }

}
