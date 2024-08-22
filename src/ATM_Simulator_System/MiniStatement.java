package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiniStatement extends JFrame  {
    String pinnumber;
    MiniStatement(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        setTitle("Mini Statement ");

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("Indian Bank :");
        bank.setFont(new Font("Raleway", Font.BOLD, 18));
        bank.setBounds(50, 20, 200, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balancee = new JLabel();
        balancee.setBounds(20,400,300,20);
        add(balancee);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 500, 100, 30);
        add(backButton);

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new transactions(pinnumber).setVisible(true); // Go back to transactions page
            }
        });
        connectJDBC con = connectJDBC.getInstance();

        {
            try (Connection conn = con.getConnection();
                 PreparedStatement ps = conn.prepareStatement("SELECT cardnumber FROM login WHERE pinnumber = ?")) {

                ps.setString(1, pinnumber);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching card number: " + e.getMessage());
            }

            // Fetch transaction details
            try (Connection conn = con.getConnection();
                 PreparedStatement ps = conn.prepareStatement("SELECT date, type, amount FROM bank WHERE pinnumber = ?")) {

                ps.setString(1, pinnumber);
                try (ResultSet rs = ps.executeQuery()) {
                    int totalbal = 0;
                    StringBuilder miniStatementText = new StringBuilder("<html>");
                    while (rs.next()) {
                        miniStatementText.append(rs.getString("date"))
                                .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                                .append(rs.getString("type"))
                                .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                                .append(rs.getString("amount"))
                                .append("<br><br>");

                        if (rs.getString("type").equals("Deposit")) {
                            totalbal += Integer.parseInt(rs.getString("amount"));
                        } else {
                            totalbal -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    miniStatementText.append("</html>");
                    mini.setText(miniStatementText.toString());
                    balancee.setText("Your total balance is Rs = " + totalbal);
                }
            }
            catch (Exception e) {
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
        }
        mini.setBounds(20,140,400,200);
        setSize(400, 600);
        setVisible(true);
        setLocation(20, 20);
        getContentPane().setBackground(Color.white);
    }
    public static void main(String[] args) {
        new MiniStatement(" ");
    }

}
