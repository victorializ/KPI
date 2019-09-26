package com.tourism.database.Tourist;

import com.tourism.database.DBConnector;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tourist implements TouristDao {

    public void add(TouristModel tourist) {
        Connection connection = DBConnector.connect();
        String query = "INSERT INTO TOURIST (ID, name, email) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, tourist.getId());
            preparedStatement.setString(2, tourist.getName());
            preparedStatement.setString(3, tourist.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnector.disconnect(connection);
        }
    }

    public List<TouristModel> getAll() {
        Connection connection = DBConnector.connect();
        List<TouristModel> touristList = new ArrayList<TouristModel>();

        String query = "SELECT ID, NAME, EMAIL FROM TOURIST";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TouristModel tourist = new TouristModel(resultSet.getLong("ID"),
                        resultSet.getString("NAME"), resultSet.getString("EMAIL"));
                touristList.add(tourist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return touristList;
    }

    public TouristModel getById(Long id) {
        TouristModel tourist = new TouristModel();
        Connection connection = DBConnector.connect();
        String query = "SELECT * FROM TOURIST WHERE ID=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                tourist.setId(resultSet.getLong("ID"));
                tourist.setName(resultSet.getString("NAME"));
                tourist.setEmail(resultSet.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
        return tourist;
    }


    public void update(TouristModel tourist) {
        String query = "UPDATE TOURIST SET NAME=?, EMAIL=?";
        Connection connection = DBConnector.connect();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tourist.getName());
            preparedStatement.setString(2, tourist.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.disconnect(connection);
        }
    }

    public void delete(TouristModel tourist) {
        String query = "DELETE FROM TOURIST WHERE ID=?";
        Connection connection = DBConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, tourist.getId());
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
        String query = "CREATE TABLE TOURIST (ID bigint NOT NULL, NAME character varying(255) NOT NULL, " +
                "EMAIL character varying(255) NOT NULL, CONSTRAINT ID PRIMARY KEY (ID));";
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
        String query = "DROP TABLE TOURIST";
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
