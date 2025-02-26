package com.example.springbootexam.repository;

import com.example.springbootexam.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ClientDAO implements CrudOperation<Client> {
    @Autowired
    private final Connection connection;

    @Override
    public Client insert(Client client) {
        String sql = "INSERT INTO client (client_name, phone_number, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getClientName());
            statement.setString(2, client.getPhoneNumber());
            statement.setString(3, client.getEmail());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        client.setId(generatedKeys.getInt(1));
                        System.out.println("Insertion réussie avec ID : " + client.getId());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'insertion : " + e.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        String query = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                Client client = new Client(
                        result.getInt("id"),
                        result.getString("client_name"),
                        result.getString("phone_number"),
                        result.getString("email")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération : " + e.getMessage());
        }
        return clients;
    }

    @Override
    public Client getById(int id) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("id"),
                            rs.getString("client_name"),
                            rs.getString("phone_number"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Suppression réussie");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de suppression : " + e.getMessage());
        }
    }


    public Client updateClientName(String newClientName, int id) {
        String sql = "UPDATE client SET client_name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newClientName);
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Update successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }


    public Client updatePhoneNumber(String newNumber, int id) {
        String sql = "UPDATE client SET phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newNumber);
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Update successfully ");
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public List<Client> searchCart(String client){
        List<Client> clientList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM client WHERE client_name ILIKE '%s' or phone_number ILIKE '%s' or email ILIKE '%s'",client);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                clientList.add(new Client(
                                result.getInt("id"),
                                result.getString("client_name"),
                                result.getString("phone_number"),
                                result.getString("email")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return clientList;
    }

}
