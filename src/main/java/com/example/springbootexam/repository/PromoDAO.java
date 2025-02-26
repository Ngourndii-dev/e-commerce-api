package com.example.springbootexam.repository;
import com.example.springbootexam.model.Product;
import com.example.springbootexam.model.Promo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class PromoDAO implements CrudOperation<Promo> {
    @Autowired
    private final Connection connection;

    @Override
    public Promo insert(Promo promo) {
        String sql = "INSERT INTO promo(id_product, expiration_date, category) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, promo.getProduct().getId());
            statement.setDate(2, new java.sql.Date(promo.getExpirationDate().getTime()));
            statement.setString(3, promo.getCategory());
            statement.executeUpdate();

            // Récupération de l'ID généré
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    promo.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Promo inserted!");
        } catch (SQLException e) {
            System.err.println("Error during insertion: " + e.getMessage());
        }
        return promo;
    }

    @Override
    public Promo getById(int id) {
        String query = "SELECT * FROM promo WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return  new Promo(
                    result.getInt("id"),
                    result.getObject("id_product",Product.class),
                    result.getDate("expiration_date"),
                    result.getString("category")
            );
        } catch (SQLException e) {
            System.err.println("Error during retrieval: " + e.getMessage());
            return null;
        }
    }

    public Promo updatePromo(Date newExpiration, int id) {
        String sql = "UPDATE promo SET expiration_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(newExpiration.getTime()));
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Expiration updated successfully.");
                return getById(id);
            }
        } catch (SQLException e) {
            System.err.println("Error during update: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(int promoId) {
        String sql = "DELETE FROM promo WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, promoId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Promo deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }
    }

    @Override
    public List<Promo> findAll() {
        List<Promo> promos = new ArrayList<>();
        String query = "SELECT * FROM promo";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                Promo promo = new Promo(
                result.getInt("id"),
                result.getObject("id_product",Product.class),
                result.getDate("expiration_date"),
                result.getString("category")
                );
                promos.add(promo);
            }
        } catch (SQLException e) {
            System.err.println("Error during retrieval of all promos: " + e.getMessage());
        }
        return promos;
    }

    public List<Promo> searchPromo(String category){
        List<Promo> promoList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM cart WHERE type_cart ILIKE '%s'",category);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){promoList.add(new Promo(
                                result.getInt("id"),
                                result.getObject("id_product", Product.class),
                                result.getDate("expiration_date"),
                                result.getString("category")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return promoList;
    }
}
