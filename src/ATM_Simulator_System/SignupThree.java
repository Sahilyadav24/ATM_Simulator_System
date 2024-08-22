//package ATM_Simulator_System;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.sql.SQLException;
//import java.util.Random;
//import java.sql.PreparedStatement;
//
//
//public class SignupThree extends JFrame implements ActionListener {
//    JRadioButton r1,r2,r3,r4;
//    JLabel additionalDetails, accounttype, cardno, no, info, pin, pno, pininfo, service;
//    JCheckBox c1,c2,c3,c4,c5,c6,c7;
//    JButton submit, cancel, back;
//    JTextField emailField;
//    String formno;
//
//
//    SignupThree(String formno){
//        this.formno = formno;
//        setLayout(null);
//
//        setTitle("New Account Application Form - Page 3");
//
//        additionalDetails = new JLabel("Page 3 : Account Details");
//        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 25));
//        additionalDetails.setBounds(290, 40, 400, 30);
//        add(additionalDetails);
//
//        accounttype = new JLabel("Account Type");
//        accounttype.setFont(new Font("Raleway", Font.BOLD, 20));
//        accounttype.setBounds(100, 140, 200, 30);
//        add(accounttype);
//
//
//        r1 = new JRadioButton("Saving Account");
//        r1.setFont(new Font("Raleway",Font.BOLD,16));
//        r1.setBackground(Color.white);
//        r1.setBounds(100,180,150,20);
//        add(r1);
//
//        r2 = new JRadioButton("Current Account");
//        r2.setFont(new Font("Raleway",Font.BOLD,16));
//        r2.setBackground(Color.white);
//        r2.setBounds(350,180,170,20);
//        add(r2);
//
//        r3 = new JRadioButton("Fixed Account");
//        r3.setFont(new Font("Raleway",Font.BOLD,16));
//        r3.setBackground(Color.white);
//        r3.setBounds(100,220,150,20);
//        add(r3);
//
//        r4 = new JRadioButton("Recurring Account");
//        r4.setFont(new Font("Raleway",Font.BOLD,16));
//        r4.setBackground(Color.white);
//        r4.setBounds(350,220,180,20);
//        add(r4);
//
//        ButtonGroup buttonsel = new ButtonGroup();
//        buttonsel.add(r1);
//        buttonsel.add(r2);
//        buttonsel.add(r3);
//        buttonsel.add(r4);
//
//
////        cardno = new JLabel("Card Number :");
////        cardno.setFont(new Font("Raleway", Font.BOLD, 20));
////        cardno.setBounds(100, 260, 200, 30);
////        add(cardno);
////
////        no = new JLabel("xxxx-xxxx-xxxx-4526 :");
////        no.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
////        no.setBounds(330, 260, 300, 30);
////        add(no);
////
////        info = new JLabel("This is Your 16 Digit Card Number :");
////        info.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 12));
////        info.setBounds(100, 290, 300, 15);
////        add(info);
////
////        pin = new JLabel("PIN :");
////        pin.setFont(new Font("Raleway", Font.BOLD, 20));
////        pin.setBounds(100, 330, 200, 30);
////        add(pin);
////
////        pno = new JLabel("xxxx");
////        pno.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
////        pno.setBounds(330, 330, 300, 30);
////        add(pno);
////
////        pininfo = new JLabel("This is Your 4 Digit PIN Number :");
////        pininfo.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 12));
////        pininfo.setBounds(100, 360, 300, 15);
////        add(pininfo);
//
//        service = new JLabel("Services Required :");
//        service.setFont(new Font("Raleway", Font.BOLD, 20));
//        service.setBounds(100, 400, 200, 25);
//        add(service);
//
//
//        c1 = new JCheckBox("ATM CARD");
//        c1.setBackground(Color.white);
//        c1.setFont(new Font("Raleway",Font.BOLD,16));
//        c1.setBounds(100,440,200,30);
//        add(c1);
//        c2 = new JCheckBox("Internet Banking");
//        c2.setBackground(Color.white);
//        c2.setFont(new Font("Raleway",Font.BOLD,16));
//        c2.setBounds(350,440,200,30);
//        add(c2);
//        c3 = new JCheckBox("Mobile Banking");
//        c3.setBackground(Color.white);
//        c3.setFont(new Font("Raleway",Font.BOLD,16));
//        c3.setBounds(100,490,200,30);
//        add(c3);
//        c4 = new JCheckBox("EMAIL & SMS Alert");
//        c4.setBackground(Color.white);
//        c4.setFont(new Font("Raleway",Font.BOLD,16));
//        c4.setBounds(350,490,200,30);
//        add(c4);
//        c5 = new JCheckBox("Check Book");
//        c5.setBackground(Color.white);
//        c5.setFont(new Font("Raleway",Font.BOLD,16));
//        c5.setBounds(100,540,200,30);
//        add(c5);
//        c6 = new JCheckBox("E-Statement");
//        c6.setBackground(Color.white);
//        c6.setFont(new Font("Raleway",Font.BOLD,16));
//        c6.setBounds(350,540,200,30);
//        add(c6);
//        c7 = new JCheckBox("I Hearby Declare That Above Entered Details Are Correct to the Best of My Knowledge");
//        c7.setBackground(Color.white);
//        c7.setFont(new Font("Raleway",Font.BOLD,12));
//        c7.setBounds(100,590,530,30);
//        add(c7);
//
//        JLabel emailLabel = new JLabel("Email Address:");
//        emailLabel.setFont(new Font("Raleway", Font.BOLD, 20));
//        emailLabel.setBounds(100, 300, 200, 30);
//        add(emailLabel);
//
//        emailField = new JTextField();
//        emailField.setFont(new Font("Raleway", Font.BOLD, 10));
//        emailField.setBounds(330, 300, 250, 30);
//        add(emailField);
//
//        submit = new JButton("Submit");
//        submit.setBackground(Color.BLACK);
//        submit.setForeground(Color.WHITE);
//        submit.setFont(new Font("Raleway",Font.BOLD,15));
//        submit.setBounds(420,635,100,30);
//        submit.addActionListener(this);
//        add(submit);
//
//        cancel = new JButton("Cancel");
//        cancel.setBackground(Color.BLACK);
//        cancel.setForeground(Color.WHITE);
//        cancel.setFont(new Font("Raleway",Font.BOLD,15));
//        cancel.addActionListener(this);
//        cancel.setBounds(310,635,100,30);
//        add(cancel);
//
//        back = new JButton("Back");
//        back.setBackground(Color.BLACK);
//        back.setForeground(Color.WHITE);
//        back.setFont(new Font("Raleway", Font.BOLD, 15));
//        back.setBounds(200, 635, 100, 30);
//        back.addActionListener(this);
//        add(back);
//
//        getContentPane().setBackground(Color.WHITE);
//
//
//        setSize(800, 800);
//        setVisible(true);
//        setLocation(350, 50);
//    }
//    public void actionPerformed(ActionEvent ae){
//        if (ae.getSource()==submit){
//            String accounttype = null;
//            if (r1.isSelected()){
//                accounttype = "Saving Account";
//            }
//            else if (r2.isSelected()){
//                accounttype = "Fixed Deposite Account";
//            }else if (r3.isSelected()){
//                accounttype = "Current Account";
//            }else if (r4.isSelected()){
//                accounttype = "Recurring Account";
//            }
//
//            String facility = "";
//            if (c1.isSelected()){
//                facility = facility +" ATM Card";
//            }else if (c2.isSelected()){
//                facility = facility +" Internet Banking";
//            }else if (c3.isSelected()){
//                facility = facility +" Mobile Banking";
//            }else if (c4.isSelected()){
//                facility = facility +" EMAIL & SMS Alerts";
//            }else if (c5.isSelected()){
//                facility = facility +" Check Book";
//            }else if (c6.isSelected()){
//                facility = facility +" E-Statement";
//            }
//
//            try {
//                // Debug print statement
//                System.out.println("Form Number: " + formno);
//                if (formno.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Form number is required");
//            }
//                else if (accounttype == null){
//                    JOptionPane.showMessageDialog(null,"Account type is Required");
//                }
//                else if (email.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Email Address is required");
//                } else {
//                    // Generate OTP and send email
//                    String otp = generateOTP();
//                    EmailUtility.sendEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
//                    JOptionPane.showMessageDialog(null, "OTP sent to your email");
//
//                    // Transfer data to database
//                    transferDataToDatabase();
//
//                    // Generate card number and PIN
//                    Random random = new Random();
//                    String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
//                    String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);
//
//                else {
//
//                    transferDataToDatabase();
//
//
//                    Random random = new Random();
//                    String cardnumber = ""+ Math.abs((random.nextLong() % 90000000L)+5040936000000000L);
//                    String pinnumber = "" +Math.abs((random.nextLong()%9000L)+1000L);
//
//                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\nPin: " + pinnumber);
//                    setVisible(false);
//                }
//            }catch (Exception e){
//                e.printStackTrace(); // Print the stack trace for debugging
//                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//        }
//        }else if (ae.getSource()==cancel){
//            System.exit(0);
//        }
//        else if (ae.getSource() == back) {
//            new SignupTwo("").setVisible(true);
//            setVisible(false);
//        }
//    }
//        private String generateOTP() {
//            return String.valueOf((int) (Math.random() * 9000) + 1000);
//        }
//
//
//private void transferDataToDatabase() {
//    String[] files = {"SignupOne.txt", "SignupTwo.txt", "SignupThree.txt"};
//    connectJDBC con = connectJDBC.getInstance();
//
//    for (String fileName : files) {
//        File file = new File(fileName);
//        if (!file.exists() || file.length() == 0) {
//            continue; // Skip if file doesn't exist or is empty
//        }
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            String formno = "", name = "", dob = "", address = "", contact = "";
//            String education = "", occupation = "", maritalstatus = "", income = "";
//            String accounttype = "", cardnumber = "", pinnumber = "", facility = "", email = "";
//
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(": ");
//                if (data.length < 2) {
//                    continue; // Skip malformed lines
//                }
//
//                String key = data[0].trim();
//                String value = data[1].trim();
//
//                // Extract values based on the key
//                switch (key) {
//                    case "Form Number":
//                        formno = value;
//                        break;
//                    case "Name":
//                        name = value;
//                        break;
//                    case "DOB":
//                        dob = value;
//                        break;
//                    case "Address":
//                        address = value;
//                        break;
//                    case "Contact":
//                        contact = value;
//                        break;
//                    case "Education":
//                        education = value;
//                        break;
//                    case "Occupation":
//                        occupation = value;
//                        break;
//                    case "Marital Status":
//                        maritalstatus = value;
//                        break;
//                    case "Income":
//                        income = value;
//                        break;
//                    case "Account Type":
//                        accounttype = value;
//                        break;
//                    case "Card Number":
//                        cardnumber = value;
//                        break;
//                    case "PIN Number":
//                        pinnumber = value;
//                        break;
//                    case "Facility":
//                        facility = value;
//                        break;
//                    case "Email Address":
//                        email = value;
//                        break;
//                }
//            }
//
//            PreparedStatement pstmt = null;
//
//            switch (fileName) {
//                case "SignupOne.txt":
//                    String queryOne = "INSERT INTO signupone (formno, name, dob, address, contact) VALUES (?, ?, ?, ?, ?)";
//                    pstmt = con.getConnection().prepareStatement(queryOne);
//                    pstmt.setString(1, formno);
//                    pstmt.setString(2, name);
//                    pstmt.setString(3, dob);
//                    pstmt.setString(4, address);
//                    pstmt.setString(5, contact);
//                    break;
//                case "SignupTwo.txt":
//                    String queryTwo = "INSERT INTO signuptwo (formno, education, occupation, maritalstatus, income) VALUES (?, ?, ?, ?, ?)";
//                    pstmt = con.getConnection().prepareStatement(queryTwo);
//                    pstmt.setString(1, formno);
//                    pstmt.setString(2, education);
//                    pstmt.setString(3, occupation);
//                    pstmt.setString(4, maritalstatus);
//                    pstmt.setString(5, income);
//                    break;
//                case "SignupThree.txt":
//                    String queryThree = "INSERT INTO signupthree (formno, accounttype, cardnumber, pinnumber, facility) VALUES (?, ?, ?, ?, ?)";
//                    pstmt = con.getConnection().prepareStatement(queryThree);
//                    pstmt.setString(1, formno);
//                    pstmt.setString(2, accounttype);
//                    pstmt.setString(3, cardnumber);
//                    pstmt.setString(4, pinnumber);
//                    pstmt.setString(5, facility);
//                    pstmt.setString(6, email);
//                    break;
//            }
//
//            if (pstmt != null) {
//                pstmt.executeUpdate();
//                pstmt.close();
//            }
//
//            // Clear the file after transferring data
//            new FileWriter(file, false).close();
//        } catch (IOException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//        }
//    }
//
//    JOptionPane.showMessageDialog(null, "Data successfully transferred to database.");
//}
//
//    public static void main(String[] args) {
//
//        new SignupThree("").setVisible(true);
//    }
//}
package ATM_Simulator_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JLabel additionalDetails, accounttype, emailLabel, service;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel, back;
    JTextField emailField;
    String formno;

    SignupThree(String formno) {
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
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.white);
        r1.setBounds(100, 180, 150, 20);
        add(r1);

        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.white);
        r2.setBounds(350, 180, 170, 20);
        add(r2);

        r3 = new JRadioButton("Fixed Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.white);
        r3.setBounds(100, 220, 150, 20);
        add(r3);

        r4 = new JRadioButton("Recurring Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.white);
        r4.setBounds(350, 220, 180, 20);
        add(r4);

        ButtonGroup buttonsel = new ButtonGroup();
        buttonsel.add(r1);
        buttonsel.add(r2);
        buttonsel.add(r3);
        buttonsel.add(r4);

        service = new JLabel("Services Required :");
        service.setFont(new Font("Raleway", Font.BOLD, 20));
        service.setBounds(100, 400, 200, 25);
        add(service);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.white);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 440, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.white);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 440, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.white);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 490, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL & SMS Alert");
        c4.setBackground(Color.white);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 490, 200, 30);
        add(c4);

        c5 = new JCheckBox("Check Book");
        c5.setBackground(Color.white);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 540, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.white);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 540, 200, 30);
        add(c6);

        c7 = new JCheckBox("I Hereby Declare That Above Entered Details Are Correct to the Best of My Knowledge");
        c7.setBackground(Color.white);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 590, 530, 30);
        add(c7);

        emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        emailLabel.setBounds(100, 300, 200, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Raleway", Font.BOLD, 10));
        emailField.setBounds(330, 300, 250, 30);
        add(emailField);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 15));
        submit.setBounds(420, 635, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 15));
        cancel.addActionListener(this);
        cancel.setBounds(310, 635, 100, 30);
        add(cancel);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 15));
        back.setBounds(200, 635, 100, 30);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 800);
        setVisible(true);
        setLocation(350, 50);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accounttype = null;
            if (r1.isSelected()) {
                accounttype = "Saving Account";
            } else if (r2.isSelected()) {
                accounttype = "Current Account";
            } else if (r3.isSelected()) {
                accounttype = "Fixed Account";
            } else if (r4.isSelected()) {
                accounttype = "Recurring Account";
            }

            String facility = "";
            if (c1.isSelected()) {
                facility += " ATM Card";
            }
            if (c2.isSelected()) {
                facility += " Internet Banking";
            }
            if (c3.isSelected()) {
                facility += " Mobile Banking";
            }
            if (c4.isSelected()) {
                facility += " EMAIL & SMS Alerts";
            }
            if (c5.isSelected()) {
                facility += " Check Book";
            }
            if (c6.isSelected()) {
                facility += " E-Statement";
            }

            String email = emailField.getText().trim();

            try {
                if (formno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Form number is required");
                } else if (accounttype == null) {
                    JOptionPane.showMessageDialog(null, "Account type is required");
                } else if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email Address is required");
                } else {
                    // Generate OTP and send email
                    String otp = generateOTP();
                    EmailUtility.sendEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
                    JOptionPane.showMessageDialog(null, "OTP sent to your email");

                    // Transfer data to database
                    transferDataToDatabase();

                    // Generate card number and PIN
                    Random random = new Random();
                    String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
                    String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\nPin: " + pinnumber);
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == cancel) {
            System.exit(0);
        } else if (ae.getSource() == back) {
            new SignupTwo("").setVisible(true);
            setVisible(false);
        }
    }

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    private void transferDataToDatabase() {
        String[] files = {"SignupOne.txt", "SignupTwo.txt", "SignupThree.txt"};
        connectJDBC con = connectJDBC.getInstance();

        for (String fileName : files) {
            File file = new File(fileName);
            if (!file.exists() || file.length() == 0) {
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String formno = "", name = "", dob = "", address = "", contact = "";
                String education = "", occupation = "", maritalstatus = "", income = "";
                String accounttype = "", cardnumber = "", pinnumber = "", facility = "", email = "";

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(": ");
                    if (data.length < 2) {
                        continue;
                    }

                    String key = data[0].trim();
                    String value = data[1].trim();

                    switch (key) {
                        case "Form Number":
                            formno = value;
                            break;
                        case "Name":
                            name = value;
                            break;
                        case "DOB":
                            dob = value;
                            break;
                        case "Address":
                            address = value;
                            break;
                        case "Contact":
                            contact = value;
                            break;
                        case "Education":
                            education = value;
                            break;
                        case "Occupation":
                            occupation = value;
                            break;
                        case "Marital Status":
                            maritalstatus = value;
                            break;
                        case "Income":
                            income = value;
                            break;
                        case "Account Type":
                            accounttype = value;
                            break;
                        case "Card Number":
                            cardnumber = value;
                            break;
                        case "PIN Number":
                            pinnumber = value;
                            break;
                        case "Facility":
                            facility = value;
                            break;
                        case "Email Address":
                            email = value;
                            break;
                    }
                }

                PreparedStatement pstmt = null;

                switch (fileName) {
                    case "SignupOne.txt":
                        String queryOne = "INSERT INTO signupone (formno, name, dob, address, contact) VALUES (?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryOne);
                        pstmt.setString(1, formno);
                        pstmt.setString(2, name);
                        pstmt.setString(3, dob);
                        pstmt.setString(4, address);
                        pstmt.setString(5, contact);
                        break;
                    case "SignupTwo.txt":
                        String queryTwo = "INSERT INTO signuptwo (formno, education, occupation, maritalstatus, income) VALUES (?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryTwo);
                        pstmt.setString(1, formno);
                        pstmt.setString(2, education);
                        pstmt.setString(3, occupation);
                        pstmt.setString(4, maritalstatus);
                        pstmt.setString(5, income);
                        break;
                    case "SignupThree.txt":
                        String queryThree = "INSERT INTO signupthree (formno, accounttype, cardnumber, pinnumber, facility, email) VALUES (?, ?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryThree);
                        pstmt.setString(1, formno);
                        pstmt.setString(2, accounttype);
                        pstmt.setString(3, cardnumber);
                        pstmt.setString(4, pinnumber);
                        pstmt.setString(5, facility);
                        pstmt.setString(6, email);
                        break;
                }

                if (pstmt != null) {
                    pstmt.executeUpdate();
                    pstmt.close();
                }

                new FileWriter(file, false).close();
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Data successfully transferred to database.");
    }

    public static void main(String[] args) {
        new SignupThree("").setVisible(true);
    }
}

