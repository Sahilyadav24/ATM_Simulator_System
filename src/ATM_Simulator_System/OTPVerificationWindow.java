package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OTPVerificationWindow extends JFrame implements ActionListener {
    private JTextField otpField;
    private JPasswordField newPasswordField;
    private String email;
    private String generatedOtp;

    public OTPVerificationWindow(String email, String otp) {
        this.email = email;
        this.generatedOtp = otp;

        setTitle("OTP Verification");
        setLayout(null);

        // OTP Label and Field
        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setBounds(50, 50, 100, 30);
        add(otpLabel);

        otpField = new JTextField();
        otpField.setBounds(150, 50, 200, 30);
        add(otpField);

        // New Password Label and Field
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(50, 100, 100, 30);
        add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(150, 100, 200, 30);
        add(newPasswordField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 150, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);

        // Frame Settings
        setSize(400, 300);
        setLocation(500, 300);
        getContentPane().setBackground(Color.cyan);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String enteredOtp = otpField.getText().trim();
        String newPassword = new String(newPasswordField.getPassword()).trim();

        // OTP Validation
        if (!enteredOtp.equals(generatedOtp)) {
            JOptionPane.showMessageDialog(null, "Invalid OTP. Please try again.");
        }
        // Password Validation
        else if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
        }
        // Change Password in Database
        else {
            if (changePasswordInDatabase(email, newPassword)) {
                JOptionPane.showMessageDialog(null, "Password changed successfully.");
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Password update failed. Try again.");
            }
        }
    }
private boolean changePasswordInDatabase(String email, String newPassword) {
    String query = "UPDATE Signupthree SET pinnumber = ? WHERE email = ?";

    // Use the singleton connection instance
    try (Connection con = connectJDBC.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, newPassword);
        ps.setString(2, email);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;  // Check if update was successful

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        return false;
    }
}
}
