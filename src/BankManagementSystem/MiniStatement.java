package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

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

        try {
            connectJDBC conn = new connectJDBC();
            ResultSet rs = conn.s.executeQuery("select * from login where pinnumber ='"+pinnumber+"'");
            while (rs.next()){
                card.setText("Card Number: "+rs.getString("cardnumber").substring(0,4)+"XXXXXXXX"+rs.getString("cardnumber").substring(12));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            connectJDBC conn = new connectJDBC();
            int totalbal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pinnumber ='"+pinnumber+"'");
            while (rs.next()){
                mini.setText(mini.getText()+"<html>"+rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br><html>");
                if (rs.getString("type").equals("Deposit")) {
                    totalbal += Integer.parseInt(rs.getString("Amount"));
                } else {
                    totalbal -= Integer.parseInt(rs.getString("Amount"));
                }
            }
            balancee.setText("Your total balance is Rs ="+totalbal);
        }catch (Exception e){
            System.out.println(e);
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
