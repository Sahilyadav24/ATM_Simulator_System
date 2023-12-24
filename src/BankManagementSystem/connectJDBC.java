package BankManagementSystem;
import java.sql.*;

public class connectJDBC {
    //      steps to follow
    //1. register the driver
    //2. create connection
    //3. create statement
    //4. Execute Quarry
    //5. close connecetion

    public  Statement s;
    Connection c;
    public connectJDBC(){//connection is created here
        try {
            String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
            String username = "root";
            String password = "root123";

            Connection c =DriverManager.getConnection(url,username,password);
            s= c.createStatement();
            System.out.println("connection successful");
//            c.close();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
