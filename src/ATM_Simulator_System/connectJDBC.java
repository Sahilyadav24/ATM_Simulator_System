package ATM_Simulator_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectJDBC {

    private static connectJDBC instance;
    private Connection c;
    public Statement s;

    // Private constructor to prevent instantiation from outside
    private connectJDBC() {
        createConnection();
    }

    // Method to create a connection
    private void createConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
            String username = "root";
            String password = "root";

            c = DriverManager.getConnection(url, username, password);
            s = c.createStatement();
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An unexpected exception occurred: " + e.getMessage());
        }
    }

    // Public static method to provide access to the single instance
    public static connectJDBC getInstance() {
        if (instance == null) {
            synchronized (connectJDBC.class) {
                if (instance == null) {
                    instance = new connectJDBC();
                }
            }
        }
        return instance;
    }

    // Method to get the connection
    public Connection getConnection() {
        try {
            if (c == null || c.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception occurred: " + e.getMessage());
        }
        return c;
    }

    // Optional: Method to close the connection
    public void closeConnection() {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception occurred: " + e.getMessage());
        }
    }
}
