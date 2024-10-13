package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton singup,clear,singin,otpRequest,forgotCardNumberButton;
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

        otpRequest = new JButton("Forgot Password");
        otpRequest.setBounds(300, 380, 200, 30);
        otpRequest.setBackground(Color.black);
        otpRequest.setForeground(Color.WHITE);
        otpRequest.addActionListener(this);
        add(otpRequest);


        forgotCardNumberButton = new JButton("Forgot Card Number");
        forgotCardNumberButton.setBounds(300, 420, 200, 30);
        forgotCardNumberButton.setBackground(Color.black);
        forgotCardNumberButton.setForeground(Color.WHITE);
        forgotCardNumberButton.addActionListener(this);
        add(forgotCardNumberButton);

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
                    String userEmail = getUserEmailByCardNumber(cardnumber);
                    if (userEmail != null) {
                        EmailUtility.sendEmail(userEmail, "Unsuccessful Login Attempt", "Your card number was found, but the PIN you entered is incorrect.");
                    }
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
        else if (ae.getSource() == otpRequest) {
            String enteredEmail = JOptionPane.showInputDialog("Enter your registered email:").trim();

            if (enteredEmail != null && !enteredEmail.isEmpty()) {
                String[] userDetails = getUserDetailsByEmail(enteredEmail);

                if (userDetails != null) {  // Email found in the database
                    String otp = generateOTP();
                    String emailContent = String.format(
                            "Your OTP code for password recovery is: %s\nYour Account Type is: %s",
                            otp, userDetails[1]
                    );

                    EmailUtility.sendEmail(enteredEmail, "Your OTP Code", emailContent);
                    JOptionPane.showMessageDialog(null, "An OTP has been sent to your registered email.");

                    // Open OTP verification window for password reset
                    new OTPVerificationWindow(enteredEmail, otp).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Email not found.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email cannot be empty.");
            }
        }


        else if (ae.getSource() == forgotCardNumberButton) {
            String userEmail = JOptionPane.showInputDialog("Enter your registered email:");
            if (userEmail != null && !userEmail.isEmpty()) {
                // Generate and send OTP
                String otp = generateOTP();
                EmailUtility.sendEmail(userEmail, "Your OTP Code", "Your OTP code for card number retrieval is: " + otp);
                JOptionPane.showMessageDialog(this, "An OTP has been sent to your registered email.");

                // Open OTP verification window
                new OTPForCardNumberRetrievalWindow(userEmail, otp).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Email cannot be empty.");
            }
        }

    }

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 1000000));
    }
private String getUserEmailByCardNumber(String cardnumber) {
    String query = "SELECT email FROM Signupthree WHERE cardnumber = ?";
    try (PreparedStatement ps = connectJDBC.getInstance().getConnection().prepareStatement(query)) {
        ps.setString(1, cardnumber);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("email");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public String[] getUserDetailsByEmail(String email) {
    String query = "SELECT email, accountType FROM Signupthree WHERE email = ?";
    try (PreparedStatement ps = connectJDBC.getInstance().getConnection().prepareStatement(query)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new String[]{rs.getString("email"), rs.getString("accountType")};
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public static void main(String[] args) {
        new Login();
    }
}