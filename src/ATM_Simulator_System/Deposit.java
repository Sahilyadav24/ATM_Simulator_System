package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JTextField amount;
    JButton back,deposit;
    String pinnumber;
    Deposit(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imagee = new JLabel(i3);
        imagee.setBounds(0, 0, 900, 900);
        add(imagee);

        JLabel text = new JLabel("Enter the Amount you want to Deposit");
        text.setBounds(170,300,400,20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
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
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==deposit){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals(" ")){
                JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
            }
            else {
                connectJDBC com = new connectJDBC();
                String query = "Insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')";

                try {
                    com.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs"+number+"Deposited Successfully");
                    setVisible(false);
                    new transactions(pinnumber).setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if (ae.getSource()==back){
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String[] args) {

        new Deposit(" ");
    }
}
