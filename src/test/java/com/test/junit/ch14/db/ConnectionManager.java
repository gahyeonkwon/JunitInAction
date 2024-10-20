package com.test.junit.ch14.db;

import java.sql.*;

public class ConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {
        return  connection;
    }

    public static Connection openConnection() {

        try {

            // h2 드라이버 세팅
            Class.forName("org.h2.Driver");

            // url, user(id), password 세팅
            connection = DriverManager.getConnection("jdbc:h2:~/passenger", "sa", "");

            return  connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeConnection() {
        if(null != connection)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
