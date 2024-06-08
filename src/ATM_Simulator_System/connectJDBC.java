package ATM_Simulator_System;
import java.sql.*;

public class connectJDBC {
    //      steps to follow
    //1. register the driver
    //2. create connection
    //3. create statement
    //4. Execute Quarry
    //5. close connecetion

    public  Statement s;
    public connectJDBC(){//connection is created here
        try {
            String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
            String username = "root";
            String password = "root";

            Connection c =DriverManager.getConnection(url,username,password);
            s= c.createStatement();
            System.out.println("connection successful");

    } catch (SQLException e) {
        // Print the stack trace for detailed information
        e.printStackTrace();
        System.out.println("SQL Exception occurred: " + e.getMessage());
    } catch (Exception e) {
        // Print the stack trace for detailed information
        e.printStackTrace();
        System.out.println("An unexpected exception occurred: " + e.getMessage());
    }
    }
}
