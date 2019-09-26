package com.tourism.database.Order;

import com.tourism.database.DBConnector;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order implements OrderDao {

    public void add(OrderModel order) {
        Connection connection = DBConnector.connect();
        String query = "INSERT INTO ORDERS (ID, TOURIST_ID, EQUIPMENT_ID) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, order.getTouristId());
            preparedStatement.setLong(3, order.getEquipmentId());
            preparedStatement.executeUpdate();
            System.out.println("Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnector.disconnect(connection);
        }
    }

    public List<OrderModel> getAll() {
        Connection connection = DBConnector.connect();
        List<OrderModel> orderList = new ArrayList<OrderModel>();

        String query = "SELECT ID, TOURIST_ID, EQUIPMENT_ID FROM ORDERS";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                OrderModel order = new OrderModel(resultSet.getLong("ID"),
                        resultSet.getLong("TOURIST_ID"), (long) resultSet.getFloat("EQUIPMENT_ID"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return orderList;
    }

    public OrderModel getById(Long id) {
        OrderModel order = new OrderModel();
        Connection connection = DBConnector.connect();
        String query = "SELECT * FROM ORDERS WHERE ID=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                order.setId(resultSet.getLong("ID"));
                order.setTouristId(resultSet.getLong("TOURIST_ID"));
                order.setEquipmentId(resultSet.getLong("EQUIPMENT_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return order;
    }


    public void update(OrderModel order) {
        String query = "UPDATE ORDERS SET TOURIST_ID=?, EQUIPMENT_ID=?";
        Connection connection = DBConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getTouristId());
            preparedStatement.setLong(2, order.getEquipmentId());
            preparedStatement.executeUpdate();
            System.out.println("Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public void delete(OrderModel order) {
        String query = "DELETE FROM ORDER WHERE ID=?";
        Connection connection = DBConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public void newTable() {
        Connection connection = DBConnector.connect();
        String query = "CREATE TABLE ORDERS (ID bigint NOT NULL, TOURIST_ID bigint NOT NULL, EQUIPMENT_ID bigint NOT NULL, CONSTRAINT ID PRIMARY KEY (ID));";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public void deleteTable() {
        Connection connection = DBConnector.connect();
        String query = "DROP TABLE ORDERS";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Table deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

}
