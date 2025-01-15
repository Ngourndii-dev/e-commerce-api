package com.example.springbootexam.repository;

import com.example.springbootexam.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserDAO implements CrudOperation<User> {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public User insert(User user) {
        String sql = "INSERT INTO users (username, email, occupation, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getOccupation());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            System.out.println("User inserted with ID: " + user.getId());
        } catch (SQLException e) {
            System.err.println("Error during user insertion: " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("occupation"),
                        result.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error during fetch: " + e.getMessage());
        }
        return users;
    }

    public User updateOccupation(String newOccupation, int id) {
        String sql = "UPDATE users SET occupation = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newOccupation);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Occupation updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error during update: " + e.getMessage());
        }
        return null;
    }

    public List<User> searchByOccupation(String occupation) {
        String sql = "SELECT * FROM users WHERE occupation ILIKE '%S'";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("occupation"),
                        result.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error during fetch: " + e.getMessage());
        }
        return users;
    }

    public void deleteByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error during deletion: " + e.getMessage());
        }
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                    return new User(
                            result.getInt("id"),
                            result.getString("username"),
                            result.getString("email"),
                            result.getString("occupation"),
                            result.getString("password")
                    );

            }
        } catch (SQLException e) {
            System.err.println("Error during fetch by ID: " + e.getMessage());
            return null;
        }
    }
}
