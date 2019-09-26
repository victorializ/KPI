package com.tourism.database.Equipment;

import com.tourism.database.DBConnector;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Equipment implements EquipmentDao {

    public void add(EquipmentModel equipment) {
        Connection connection = DBConnector.connect();
        String query = "INSERT INTO EQUIPMENT (ID, NAME, PRICE, TYPE) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, equipment.getId());
            preparedStatement.setString(2, equipment.getName());
            preparedStatement.setFloat(3, equipment.getPrice());
            preparedStatement.setString(4, equipment.getType());
            preparedStatement.executeUpdate();
            System.out.println("Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public List<EquipmentModel> getAll() {
        Connection connection = DBConnector.connect();
        List<EquipmentModel> equipmentList = new ArrayList<EquipmentModel>();

        String query = "SELECT ID, NAME, PRICE, TYPE FROM EQUIPMENT";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                EquipmentModel product = new EquipmentModel(resultSet.getLong("ID"), resultSet.getString("NAME"),
                        resultSet.getFloat("PRICE"), resultSet.getString("TYPE"));
                equipmentList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return equipmentList;
    }

    public EquipmentModel getById(Long id) {
        EquipmentModel equipment = new EquipmentModel();
        Connection connection = DBConnector.connect();
        String query = "SELECT * FROM EQUIPMENT WHERE ID=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                equipment.setId(resultSet.getLong("ID"));
                equipment.setName(resultSet.getString("NAME"));
                equipment.setPrice(resultSet.getFloat("PRICE"));
                equipment.setType(resultSet.getString("TYPE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return equipment;
    }


    public void update(EquipmentModel equipment) {
        String query = "UPDATE EQUIPMENT SET NAME=?, PRICE=?, TYPE=?";
        Connection connection = DBConnector.connect();
        try {
            PreparedStatement  preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setFloat(2, equipment.getPrice());
            preparedStatement.setString(3, equipment.getType());
            preparedStatement.executeUpdate();
            System.out.println("Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public void delete(EquipmentModel equipment) {
        String query = "DELETE FROM EQUIPMENT WHERE ID=?";
        Connection connection = DBConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, equipment.getId());
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
        String query =
                "CREATE TABLE EQUIPMENT (ID bigint NOT NULL, NAME character varying(255) NOT NULL, PRICE bigint NOT NULL, " +
                        "TYPE character varying(255) NOT NULL, CONSTRAINT ID PRIMARY KEY (ID));";
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
        String query = "drop table EQUIPMENT";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Table deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

}
