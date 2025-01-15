package com.example.springbootexam.repository;

import com.example.springbootexam.model.Client;
import com.example.springbootexam.model.Order;
import com.example.springbootexam.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Repository
public class OrderDAO implements CrudOperation<Order> {
    private final Connection connection;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    public OrderDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Order insert(Order order) {
        String sql = "INSERT INTO orders (order_date, status, quantity, total_price, id_client, id_product) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBoolean(2, order.getStatus());
            statement.setInt(3, order.getQuantity());
            statement.setFloat(4, order.getTotalPrice());
            statement.setInt(5, order.getClient().getId());
            statement.setInt(6, order.getProduct().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Order inserted.");
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Order order = mapResultSetToOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Order deleted : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    public Order updateQuantity(int id, int newQuantity) {
        String sql = "UPDATE orders SET quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Quantity updates : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return getById(id);
    }

    private Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setOrderDate(resultSet.getDate("order_date"));
        order.setStatus(resultSet.getBoolean("status"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setTotalPrice(resultSet.getFloat("total_price"));

        int clientId = resultSet.getInt("id_client");
        Client client = fetchClientById(clientId);
        order.setClient(client);

        int productId = resultSet.getInt("id_product");
        Product product = fetchProductById(productId);
        order.setProduct(product);

        return order;
    }

    private Client fetchClientById(int id) {
        return clientDAO.getById(id);
    }

    private Product fetchProductById(int id) {
        return productDAO.getById(id);
    }
    public List<Order> searchProduct(Date date){
        List<Order> orderList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM orders WHERE order_date ILIKE '%s'",date);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                orderList.add(new Order(
                                result.getInt("id"),
                                result.getDate("order_date"),
                                result.getBoolean("status"),
                                result.getInt("quantity"),
                                result.getFloat("total_price"),
                                result.getObject("id_client", Client.class),
                                result.getObject("id_product",Product.class)
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orderList;
    }

}
