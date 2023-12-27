package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FastCash extends JFrame implements ActionListener {
    JButton withdrawal, fastcash, ministatement, pinchange, deposit, balanceenquiry, exit;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        withdrawal = new JButton("Rs 100");
        withdrawal.setBounds(355, 415, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastcash = new JButton("Rs 500");
        fastcash.setBounds(355, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Rs 1000");
        ministatement.setBounds(170, 450, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Rs 2000");
        pinchange.setBounds(170, 415, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        deposit = new JButton("Rs 5000");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        balanceenquiry = new JButton("Rs 10000");
        balanceenquiry.setBounds(170, 485, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("BACK");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);
            connectJDBC con = new connectJDBC();
            try {
                ResultSet rs = con.s.executeQuery("select * from bank where pinnumber ='" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("Amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("Amount"));
                    }
                }

                if (ae.getSource() != exit && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Date date = new Date(System.currentTimeMillis());
                String query = "INSERT INTO bank (pinnumber, date, type, amount) VALUES('" + pinnumber + "','" + date + "','Withdrawal','" + amount + "')";

                con.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs" + amount + " Debited Successfully");
                setVisible(false);
                new transactions(pinnumber).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (con != null && con.s != null) {
                    try {
                        con.s.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        new FastCash(" ");
    }
}
