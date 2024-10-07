package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Step 1: Create a private static instance of this class
    private static DatabaseConnection instance;

    // Step 2: Define the connection details
    private static final String URL = "jdbc:mysql://localhost:3306/TP_CATAL";
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = ""; // Replace with your database password
    private Connection connection;

    // Step 3: Private constructor to prevent instantiation
    private DatabaseConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // Step 4: Public method to provide access to the instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    // Step 5: Public method to get the connection
    public Connection getConnection() {
        return connection;
    }
}
