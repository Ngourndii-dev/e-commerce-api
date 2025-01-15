package com.example.springbootexam.repository;

import com.example.springbootexam.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ProductDAO implements CrudOperation<Product> {

    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    public void stockProduct() {
        String query = "SELECT * FROM product WHERE status = 'unavailable'";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                System.out.println("Product ID: " + result.getInt("id"));
                System.out.println("Product Name: " + result.getString("product_name"));
                System.out.println("Price: " + result.getFloat("price"));
                System.out.println("Category: " + result.getString("category"));
                System.out.println("Description: " + result.getString("description"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving stock: {0}", e.getMessage());
        }
    }
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Product product = new Product(
                        result.getInt("id"),
                        result.getString("product_name"),
                        result.getString("status"),
                        result.getFloat("price"),
                        result.getString("category"),
                        result.getString("description")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving products: {0}", e.getMessage());
        }
        return products;
    }
    public Product updateCategory(String newCategory, int id) {
        String sql = "UPDATE product SET category = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newCategory);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("Category updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating category: {0}", e.getMessage());
        }
        return null;
    }
    public List<Product> searchProduct(String product){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM product WHERE product_name ILIKE '%s' or  status ILIKE '%s' or category ILIKE '%s' or description ILIKE '%s'",product);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                        result.getInt("id"),
                        result.getString("product_name"),
                        result.getString("status"),
                        result.getFloat("price"),
                        result.getString("category"),
                        result.getString("description")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    @Override
    public void deleteById(int productId) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                LOGGER.info("Product deleted successfully");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting product: {0}", e.getMessage());
        }
    }

    @Override
    public Product insert(Product product) {
        String sql = "INSERT INTO product (id, product_name, status, price, category, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getStatus());
            statement.setFloat(4, product.getPrice());
            statement.setString(5, product.getCategory());
            statement.setString(6, product.getDescription());
            statement.executeUpdate();
            LOGGER.info("Product inserted successfully");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting product: {0}", e.getMessage());
        }
        return product;
    }

    @Override
    public Product getById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Product(
                            result.getInt("id"),
                            result.getString("product_name"),
                            result.getString("status"),
                            result.getFloat("price"),
                            result.getString("category"),
                            result.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving product by ID: {0}", e.getMessage());
        }
        return null;
    }
}
