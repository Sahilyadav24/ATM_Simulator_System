package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    JRadioButton r1,r2,r3,r4;
    JLabel additionalDetails, accounttype,cardno,no,info,pin,pno,pininfo,service;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit, cancel;
    String formno;
    SignupThree(String formno){
        this.formno = formno;

        setLayout(null);

        setTitle("New Account Application Form - Page 3");


        additionalDetails = new JLabel("Page 3 : Account Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 25));
        additionalDetails.setBounds(290, 40, 400, 30);
        add(additionalDetails);



        accounttype = new JLabel("Account Type");
        accounttype.setFont(new Font("Raleway", Font.BOLD, 20));
        accounttype.setBounds(100, 140, 200, 30);
        add(accounttype);


        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBackground(Color.white);
        r1.setBounds(100,180,150,20);
        add(r1);

        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBackground(Color.white);
        r2.setBounds(350,180,170,20);
        add(r2);

        r3 = new JRadioButton("Fixed Account");
        r3.setFont(new Font("Raleway",Font.BOLD,16));
        r3.setBackground(Color.white);
        r3.setBounds(100,220,150,20);
        add(r3);

        r4 = new JRadioButton("Recurring Account");
        r4.setFont(new Font("Raleway",Font.BOLD,16));
        r4.setBackground(Color.white);
        r4.setBounds(350,220,180,20);
        add(r4);

        ButtonGroup buttonsel = new ButtonGroup();
        buttonsel.add(r1);
        buttonsel.add(r2);
        buttonsel.add(r3);
        buttonsel.add(r4);


        cardno = new JLabel("Card Number :");
        cardno.setFont(new Font("Raleway", Font.BOLD, 20));
        cardno.setBounds(100, 260, 200, 30);
        add(cardno);

        no = new JLabel("xxxx-xxxx-xxxx-4526 :");
        no.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        no.setBounds(330, 260, 300, 30);
        add(no);

        info = new JLabel("This is Your 16 Digit Card Number :");
        info.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        info.setBounds(100, 290, 300, 15);
        add(info);

        pin = new JLabel("PIN :");
        pin.setFont(new Font("Raleway", Font.BOLD, 20));
        pin.setBounds(100, 330, 200, 30);
        add(pin);

        pno = new JLabel("xxxx");
        pno.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        pno.setBounds(330, 330, 300, 30);
        add(pno);

        pininfo = new JLabel("This is Your 4 Digit PIN Number :");
        pininfo.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        pininfo.setBounds(100, 360, 300, 15);
        add(pininfo);

        service = new JLabel("Services Required :");
        service.setFont(new Font("Raleway", Font.BOLD, 20));
        service.setBounds(100, 400, 200, 25);
        add(service);


        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.white);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(100,440,200,30);
        add(c1);
        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.white);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(350,440,200,30);
        add(c2);
        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.white);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(100,490,200,30);
        add(c3);
        c4 = new JCheckBox("EMAIL & SMS Alert");
        c4.setBackground(Color.white);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(350,490,200,30);
        add(c4);
        c5 = new JCheckBox("Check Book");
        c5.setBackground(Color.white);
        c5.setFont(new Font("Raleway",Font.BOLD,16));
        c5.setBounds(100,540,200,30);
        add(c5);
        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.white);
        c6.setFont(new Font("Raleway",Font.BOLD,16));
        c6.setBounds(350,540,200,30);
        add(c6);
        c7 = new JCheckBox("I Hearby Declare That Above Entered Details Are Correct to the Best of My Knowledge");
        c7.setBackground(Color.white);
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        c7.setBounds(100,590,530,30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway",Font.BOLD,15));
        submit.setBounds(420,635,100,30);
        submit.addActionListener(this);
        add(submit);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway",Font.BOLD,15));
        cancel.addActionListener(this);
        cancel.setBounds(310,635,100,30);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);


        setSize(800, 800);
        setVisible(true);
        setLocation(350, 50);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==submit){
            String accounttype=null;
            if (r1.isSelected()){
                accounttype = "Saving Account";
            }
            else if (r2.isSelected()){
                accounttype = "Fixed Deposite Account";
            }else if (r3.isSelected()){
                accounttype = "Current Account";
            }else if (r4.isSelected()){
                accounttype = "Recurring Account";
            }

            Random random = new Random();
            String cardnumber = ""+ Math.abs((random.nextLong() % 90000000L)+5040936000000000L);
            String pinnumber = "" +Math.abs((random.nextLong()%9000L)+1000L);

            String facility = "";
            if (c1.isSelected()){
                facility = facility +" ATM Card";
            }else if (c2.isSelected()){
                facility = facility +" Internet Banking";
            }else if (c3.isSelected()){
                facility = facility +" Mobile Banking";
            }else if (c4.isSelected()){
                facility = facility +" EMAIL & SMS Alerts";
            }else if (c5.isSelected()){
                facility = facility +" Check Book";
            }else if (c6.isSelected()){
                facility = facility +" E-Statement";
            }
            try {
                if (accounttype.equals("")){
                    JOptionPane.showMessageDialog(null,"Account type is Required");
                }
                else {
                    connectJDBC cot = new connectJDBC();
                    String queryone = "insert into signupthree values ('"+formno+"','"+accounttype+"','"+cardnumber+"','"+pinnumber+"','"+facility+"')";
                    String querytwo = "insert into Login values ('"+formno+"','"+cardnumber+"','"+pinnumber+"')";

                    cot.s.executeUpdate(queryone);
                    cot.s.executeUpdate(querytwo);

                    //signup3 object
                    new SignupThree(formno);
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\nPin: " + pinnumber);
                }
            }catch (Exception e){
                e.printStackTrace(); // Print the stack trace for debugging
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        }else if (ae.getSource()==cancel){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
