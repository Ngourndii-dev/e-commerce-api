package com.example.springbootexam.repository;

import com.example.springbootexam.model.Product;
import com.example.springbootexam.model.Review;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewDAO implements CrudOperation<Review> {
    @Autowired
    private final Connection connection;
    @Override
    public Review insert(Review review) {
        String sql = "INSERT INTO review (author, id_product, rating, comment) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, review.getAuthor());
            statement.setInt(2, review.getProduct().getId());
            statement.setInt(3, review.getRating());
            statement.setString(4, review.getComment());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    review.setId(generatedKeys.getInt(1)); // Récupération de l'ID généré
                }
            }
            System.out.println("Review inserted!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return review;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.id, r.author, r.id_product, r.rating, r.comment, p.product_name, p.price FROM review r " +
                "JOIN product p ON r.id_product = p.id";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                Product product = new Product(
                        result.getInt("id_product"),
                        result.getString("product_name"),
                        null,
                        result.getFloat("price"),
                        null,
                        null
                );

                Review review = new Review(
                        result.getInt("id"),
                        result.getString("author"),
                        product,
                        result.getInt("rating"),
                        result.getString("comment")
                );
                reviews.add(review);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return reviews;
    }

    @Override
    public Review getById(int id) {
        String sql = "SELECT r.id, r.author, r.id_product, r.rating, r.comment, p.product_name, p.price FROM review r " +
                "JOIN product p ON r.id_product = p.id WHERE r.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
                    Product product = new Product(
                            result.getInt("id_product"),
                            result.getString("product_name"),
                            null,
                            result.getFloat("price"),
                            null,
                            null
                    );

                    return new Review(
                            result.getInt("id"),
                            result.getString("author"),
                            product,
                            result.getInt("rating"),
                            result.getString("comment")
                    );

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteById(int reviewId) {
        String sql = "DELETE FROM review WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reviewId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Review deleted!");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List<String> findAllComments() {
        List<String> comments = new ArrayList<>();
        String sql = "SELECT comment FROM review";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                comments.add(result.getString("comment"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return comments;
    }
}
