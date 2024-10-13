package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordResetWindow extends JFrame implements ActionListener {
    JTextField newPasswordField, confirmPasswordField;
    JButton resetButton;
    String cardNumber;
    String pinNumber;

    PasswordResetWindow(String cardNumber, String pinNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;

        setTitle("Reset Password");
        setLayout(null);
        setSize(400, 300);
        setLocation(500, 300);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        newPasswordLabel.setBounds(50, 50, 150, 30);
        add(newPasswordLabel);

        newPasswordField = new JTextField();
        newPasswordField.setBounds(200, 50, 150, 30);
        add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        confirmPasswordLabel.setBounds(50, 100, 200, 30);
        add(confirmPasswordLabel);

        confirmPasswordField = new JTextField();
        confirmPasswordField.setBounds(200, 100, 150, 30);
        add(confirmPasswordField);

        resetButton = new JButton("Reset Password");
        resetButton.setBounds(100, 150, 200, 30);
        resetButton.addActionListener(this);
        add(resetButton);

        getContentPane().setBackground(Color.cyan);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.equals(confirmPassword)) {
            // Logic to update the password in the database
            updatePasswordInDatabase(cardNumber, newPassword);
            JOptionPane.showMessageDialog(this, "Password has been reset successfully!");
            setVisible(false);
            // Optionally, redirect to the login window or main application window
            new Login().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.");
        }
    }

    private void updatePasswordInDatabase(String cardNumber, String newPassword) {
        String query = "UPDATE login SET pinnumber = ? WHERE cardnumber = ?";

        // Use the singleton connection instance
        try (Connection connection = connectJDBC.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword); // Update to new password
            preparedStatement.setString(2, cardNumber);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("No record found with the provided card number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PasswordResetWindow("dummyCardNumber", "dummyPin"); // For testing purposes
    }
}
