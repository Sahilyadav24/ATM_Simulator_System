package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton singup,clear,singin;
    JTextField cardText;
    JPasswordField pinText;


    Login(){
        setTitle("ATM AUTOMATED TELLER MACHINE");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);

        JLabel text = new JLabel("Welcome TO ATM");
        text.setFont(new Font("ossward",Font.HANGING_BASELINE,38));
        text.setBounds(200,40,400,40);
        add(text);

        JLabel cardNumber = new JLabel("Card Number : ");
        cardNumber.setFont(new Font("ossward",Font.ROMAN_BASELINE,28));
        cardNumber.setBounds(120,150,300,40);
        add(cardNumber);

        cardText = new JTextField();
        cardText.setBounds(350,150,250,40);
        cardText.setFont(new Font("Arial",Font.BOLD,15));
        add(cardText);

        JLabel pin = new JLabel("Pin Number : ");
        pin.setFont(new Font("ossward",Font.ROMAN_BASELINE,28));
        pin.setBounds(120,220,400,40);
        add(pin);

        pinText = new JPasswordField();
        pinText.setBounds(350,220,250,40);
        pinText.setFont(new Font("Arial",Font.BOLD,15));
        add(pinText);

        singin = new JButton("Sign In ");
        singin.setBounds(300,300,100,30);
        singin.setBackground(Color.black);
        singin.setForeground(Color.WHITE);
        singin.addActionListener(this);
        add(singin);

        clear = new JButton("CLEAR ");
        clear.setBounds(420,300,100,30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        singup = new JButton("Sign UP ");
        singup.setBounds(360,340,100,30);
        singup.setBackground(Color.black);
        singup.setForeground(Color.WHITE);
        singup.addActionListener(this);
        add(singup);


        getContentPane().setBackground(Color.cyan);

        setSize(800,500);
        setVisible(true);
        setLocation(350,200);
    }
    public void actionPerformed(ActionEvent ae) {
        String cardnumber;
        if (ae.getSource() == clear) {
            cardText.setText("");
            pinText.setText("");
        } else if (ae.getSource() == singin) {
            connectJDBC con = connectJDBC.getInstance();
            cardnumber = cardText.getText();
            String pinnumber = pinText.getText();
            String query = "SELECT * FROM login WHERE cardnumber ='" + cardnumber + "' AND pinnumber ='" + pinnumber + "'";

            try {
                ResultSet rs = con.s.executeQuery(query);
                if (rs.next()) {
                    String userEmail = getUserEmailByCardNumber(cardnumber);

                    if (userEmail != null) {
                        EmailUtility.sendEmail(userEmail, "Login Successful", "You have successfully logged in.");
                    }
                    setVisible(false);
                    new transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (con != null && con.s != null) {
                    try {
                        con.s.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else if (ae.getSource() == singup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    private String getUserEmailByCardNumber (String cardnumber){
        String email = null;
        String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
        String username = "root";
        String password = "root";
        String query = "SELECT email FROM SignupThree WHERE cardnumber = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cardnumber);
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
        new Login();
    }
}