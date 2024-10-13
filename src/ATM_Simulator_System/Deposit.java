package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JTextField amount;
    JButton back, deposit;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imagee = new JLabel(i3);
        imagee.setBounds(0, 0, 900, 900);
        add(imagee);

        JLabel text = new JLabel("Enter the Amount you want to Deposit");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        imagee.add(text);

        amount = new JTextField();
        amount.setBounds(170, 350, 320, 25);
        amount.setFont(new Font("Arial", Font.BOLD, 22));
        add(amount);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.white);
        deposit.setForeground(Color.black);
        deposit.setFont(new Font("Raleway", Font.BOLD, 16));
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        imagee.add(deposit);

        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(Color.black);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        add(back);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText().trim();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (number.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
            } else {
                connectJDBC con = connectJDBC.getInstance();

                String query = "INSERT INTO bank (pinnumber, date, type, amount) VALUES (?, ?, ?, ?)";

                try (Connection conn = con.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setString(1, pinnumber);
                    pstmt.setString(2, sdf.format(date));
                    pstmt.setString(3, "Deposit");
                    pstmt.setString(4, number);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully");
                    String userEmail = getUserEmailByCardNumber(pinnumber);;
                    if (userEmail != null) {
                        EmailUtility.sendEmail(userEmail, "Deposit Notification", "You have successfully deposited Rs " + number + " into your account.");
                    }
                    setVisible(false);
                    new transactions(pinnumber).setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        }
    }

    // Assuming you have a method to get email by card number
    private String getUserEmailByCardNumber(String pinnumber) {
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
        new Deposit("");
    }
}
