package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String pinnumber;
    JButton back;

    public BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Back");
        back.setBackground(Color.RED);
        back.setForeground(Color.black);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back); // Add the back button to the image, not the frame

        connectJDBC con = connectJDBC.getInstance();

        int balance = 0;

        try (Connection conn = con.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT type, amount FROM bank WHERE pinnumber = ?")) {

            ps.setString(1, pinnumber);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount")); // Assuming the column name is "amount"
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (con != null && con.s != null) {
                try {
                    con.s.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        JLabel text = new JLabel("Your current account balance is = " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        image.add(text);

        String userEmail = getUserEmailByPinNumber(pinnumber);
        if (userEmail != null) {
            EmailUtility.sendEmail(userEmail, "Balance Enquiry Notification", "Your current account balance is Rs " + balance + ".");
        }

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    private String getUserEmailByPinNumber(String pinnumber) {
        String email = null;
        String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
        String username = "root";
        String password = "root";
        String query = "SELECT email FROM SignupThree WHERE cardnumber = (SELECT cardnumber FROM Login WHERE pinnumber = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, pinnumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static void main(String[] args) {
        new BalanceEnquiry(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new transactions(pinnumber).setVisible(true); // Fixed the parameter to pass the correct pinnumber
    }
}

