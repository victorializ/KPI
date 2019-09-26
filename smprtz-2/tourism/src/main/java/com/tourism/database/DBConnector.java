package com.tourism.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/tourism?serverTimezone=UTC";
    private static final String USERNAME = "victoria";
    private static final String PASSWORD = "pASSWORD.1";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error!");
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Disconnect error!");
        }
    }
}
