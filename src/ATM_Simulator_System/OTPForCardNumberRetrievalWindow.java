package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OTPForCardNumberRetrievalWindow extends JFrame implements ActionListener {
    private JTextField otpTextField;
    private JButton verifyOtpButton;
    private String expectedOtp;
    private String userEmail;

    public OTPForCardNumberRetrievalWindow(String userEmail, String expectedOtp) {
        this.expectedOtp = expectedOtp;
        this.userEmail = userEmail;

        setTitle("OTP Verification");
        setLayout(null);
        setSize(400, 300);
        setLocation(500, 300);

        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        otpLabel.setBounds(50, 50, 100, 30);
        add(otpLabel);

        otpTextField = new JTextField();
        otpTextField.setBounds(150, 50, 200, 30);
        add(otpTextField);

        verifyOtpButton = new JButton("Verify");
        verifyOtpButton.setBounds(150, 100, 100, 30);
        verifyOtpButton.addActionListener(this);
        add(verifyOtpButton);

        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on exit
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == verifyOtpButton) {
        String enteredOtp = otpTextField.getText(); // Get the OTP entered by the user

        if (enteredOtp.equals(expectedOtp)) {
            JOptionPane.showMessageDialog(this, "OTP Verified Successfully!");

            // Retrieve the card number by email
            String cardNumber = getCardNumberByEmail(userEmail);

            // Check if the card number was retrieved successfully
            if (cardNumber != null && !cardNumber.isEmpty()) {
                String emailContent = "Your card number is: " + cardNumber;
                System.out.println("Email Content: " + emailContent); // Debugging

                // Send email with the card number
                EmailUtility.sendEmail(userEmail, "Your Card Number", emailContent);
                JOptionPane.showMessageDialog(this, "Your card number has been sent to your email.");
            } else {
                JOptionPane.showMessageDialog(this, "No card number found for the provided email.");
            }

            dispose(); // Close the OTP window after processing
        } else {
            JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.");
        }
    }
}

private String getCardNumberByEmail(String userEmail) {
    String cardNumber = null;
    String query = "SELECT cardnumber FROM SignupThree WHERE email = ?";

    try (Connection connection = connectJDBC.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, userEmail.trim());
        System.out.println("Executing query for email: " + userEmail); // Debug

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            cardNumber = resultSet.getString("cardnumber");
            System.out.println("Card Number Retrieved: " + cardNumber); // Debug
        } else {
            System.out.println("No card number found for the provided email."); // Debug
        }
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }
    return cardNumber;
}


}
