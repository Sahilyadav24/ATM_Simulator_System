package ATM_Simulator_System;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

            // Generate card number and PIN
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
            String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);


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

                    // Create and write to SignupThree.json
                    JsonObject userData = new JsonObject();
                    userData.addProperty("formno", formno);
                    userData.addProperty("accounttype", accounttype);
                    userData.addProperty("cardnumber", cardnumber); // Generate cardnumber earlier
                    userData.addProperty("pinnumber", pinnumber); // Generate pinnumber earlier
                    userData.addProperty("facility", facility);
                    userData.addProperty("email", email);

                    FileUtils.writeToFile(userData, "Signupthree.json");

                    // Transfer data to database
                    transferDataToDatabase();

                    // Insert data into Login table
                    connectJDBC con = connectJDBC.getInstance();
                    String querytwo = "INSERT INTO Login (formno, pinnumber, cardnumber) VALUES (?, ?, ?)";

                    try (PreparedStatement pstmt = con.getConnection().prepareStatement(querytwo)) {
                        pstmt.setString(1, formno);
                        pstmt.setString(2, pinnumber);
                        pstmt.setString(3, cardnumber);

                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Successfully inserted data into Login table");
                        } else {
                            System.out.println("No rows affected for Login table");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
                    }


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
        String[] files = {"Signup.json", "Signuptwo.json", "Signupthree.json"};
        connectJDBC con = connectJDBC.getInstance();


//         Verify database connection is active
        try {
            if (con.getConnection() == null || con.getConnection().isClosed()) {
                System.out.println("No valid database connection.");
                JOptionPane.showMessageDialog(null, "No valid database connection.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
            return;
        }

        try {
            con.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to set auto-commit: " + e.getMessage());
            return;
        }


        for (String fileName : files) {
            File file = new File(fileName);
            if (!file.exists() || file.length() == 0) {
                continue;
            }
            try {
                JsonObject jsonObject = FileUtils.readFromFile(fileName);
                System.out.println("Reading from " + fileName + ": " + jsonObject.toString());


                // Extract data based on the JSON structure
                String formno = jsonObject.has("formno") ? jsonObject.get("formno").getAsString() : "";
                String name = jsonObject.has("name") ? jsonObject.get("name").getAsString() : "";
                String fname = jsonObject.has("fname") ? jsonObject.get("fname").getAsString() : "";
                String dob = jsonObject.has("dob") ? jsonObject.get("dob").getAsString() : "";
                String gender = jsonObject.has("gender") ? jsonObject.get("gender").getAsString() : "";
                String address = jsonObject.has("address") ? jsonObject.get("address").getAsString() : "";
                String contact = jsonObject.has("contact") ? jsonObject.get("contact").getAsString() : "";
                String education = jsonObject.has("education") ? jsonObject.get("education").getAsString() : "";
                String occupation = jsonObject.has("occupation") ? jsonObject.get("occupation").getAsString() : "";
                String maritalstatus = jsonObject.has("maritalstatus") ? jsonObject.get("maritalstatus").getAsString() : "";
                String income = jsonObject.has("income") ? jsonObject.get("income").getAsString() : "";
                String accounttype = jsonObject.has("accounttype") ? jsonObject.get("accounttype").getAsString() : "";
                String cardnumber = jsonObject.has("cardnumber") ? jsonObject.get("cardnumber").getAsString() : "";
                String pin = jsonObject.has("pin") ? jsonObject.get("pin").getAsString() : ""; // add pin
                String state = jsonObject.has("state") ? jsonObject.get("state").getAsString() : ""; // add state
                String marital = jsonObject.has("marital") ? jsonObject.get("marital").getAsString() : ""; // add marital
                String pinnumber = jsonObject.has("pinnumber") ? jsonObject.get("pinnumber").getAsString() : "";
                String facility = jsonObject.has("facility") ? jsonObject.get("facility").getAsString() : "";
                String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : "";
                String religion = jsonObject.has("religion") ? jsonObject.get("religion").getAsString() : "";
                String category = jsonObject.has("category") ? jsonObject.get("category").getAsString() : "";
                String pan = jsonObject.has("pan") ? jsonObject.get("pan").getAsString() : "";
                String adhar = jsonObject.has("adhar") ? jsonObject.get("adhar").getAsString() : "";
                boolean seniorcitizen = jsonObject.has("seniorcitizen") ? jsonObject.get("seniorcitizen").getAsBoolean() : false;
                System.out.println("Inserting into " + fileName + ":");
                System.out.println("formno=" + formno + ", name=" + name + ", fname=" + fname + ", dob=" + dob +
                        ", gender=" + gender + ", address=" + address + ", contact=" + contact +
                        ", education=" + education + ", occupation=" + occupation + ", maritalstatus=" + maritalstatus +
                        ", income=" + income + ", accounttype=" + accounttype + ", cardnumber=" + cardnumber +
                        ", pin=" + pin + ", state=" + state + ", marital=" + marital + ", pinnumber=" + pinnumber +
                        ", facility=" + facility + ", email=" + email + ", religion=" + religion + ", category=" + category +
                        ", pan=" + pan + ", adhar=" + adhar + ", seniorcitizen=" + seniorcitizen);



                PreparedStatement pstmt = null;

                switch (fileName) {
                    case "Signup.json":
                        String queryOne = "INSERT INTO signup (formno, name, fname, dob, gender, email, address, pin, state, marital) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryOne);
                        pstmt.setLong(1, Long.parseLong(formno));
                        pstmt.setString(2, name);
                        pstmt.setString(3, fname);
                        pstmt.setString(4, dob);
                        pstmt.setString(5, gender);
                        pstmt.setString(6, email);
                        pstmt.setString(7, address);
                        pstmt.setString(8, pin);
                        pstmt.setString(9, state);
                        pstmt.setString(10, marital);
                        break;

                    case "Signuptwo.json":
                        String queryTwo = "INSERT INTO signuptwo (education, occupation, income, religion, category, pan, adhar, seniorcitizen) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryTwo);
                        pstmt.setString(1, religion);
                        pstmt.setString(2, category);
                        pstmt.setString(3, income);
                        pstmt.setString(4, education);
                        pstmt.setString(5, occupation);
                        pstmt.setString(6, pan);
                        pstmt.setString(7, adhar);
                        pstmt.setBoolean(8, seniorcitizen);
                        break;

                    case "Signupthree.json":
                        String queryThree = "INSERT INTO signupthree (formno, accounttype, cardnumber, pinnumber, facility, email) VALUES (?, ?, ?, ?, ?, ?)";
                        pstmt = con.getConnection().prepareStatement(queryThree);
                        pstmt.setLong(1, Long.parseLong(formno));
                        pstmt.setString(2, accounttype);
                        pstmt.setString(3, cardnumber);
                        pstmt.setString(4, pinnumber);
                        pstmt.setString(5, facility);
                        pstmt.setString(6, email);
                        break;
                }

                if (pstmt != null) {
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Successfully inserted data from: " + fileName);
                        // Clear the file after successful insert
                        new FileWriter(file, false).close();
                    } else {
                        System.out.println("No rows affected for: " + fileName);
                    }
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "IO Error: " + e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(null, "Data successfully transferred to database.");
    }

    public static void main(String[] args) {
        new SignupThree("").setVisible(true);
    }
}