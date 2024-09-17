package ATM_Simulator_System;

import com.google.gson.JsonObject;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;

public class SignupOne extends JFrame implements ActionListener {

    int random;
    JTextField nametext, fnametext, emailtext, pintext, statetext, addresstext;
    JRadioButton male, female, married, unmarried;
    JButton next, back;
    JDateChooser datechoose;

    SignupOne() {

        setLayout(null);
        int min = 1000;
        int max = 9999;
        Random ran = new Random();
        random = ran.nextInt(max - min + 1) + min;

        JLabel formno = new JLabel("Application Form No " + random);
        formno.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page 1 : Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name :");
        name.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nametext = new JTextField();
        nametext.setBounds(350, 140, 250, 30);
        nametext.setFont(new Font("Arial", Font.BOLD, 15));
        add(nametext);

        JLabel fname = new JLabel("Father Name :");
        fname.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        fname.setBounds(100, 190, 150, 30);
        add(fname);

        fnametext = new JTextField();
        fnametext.setBounds(350, 190, 250, 30);
        fnametext.setFont(new Font("Arial", Font.BOLD, 15));
        add(fnametext);

        JLabel dob = new JLabel("D.O.B :");
        dob.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        dob.setBounds(100, 240, 150, 30);
        add(dob);

        datechoose = new JDateChooser();
        datechoose.setBounds(350, 240, 250, 30);
        datechoose.setForeground(new Color(105, 105, 105));
        add(datechoose);

        JLabel gender = new JLabel("Gender :");
        gender.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        gender.setBounds(100, 290, 150, 30);
        add(gender);

        male = new JRadioButton("male");
        male.setBounds(350, 290, 60, 30);
        male.setBackground(Color.pink);
        add(male);

        female = new JRadioButton("female");
        female.setBounds(450, 290, 70, 30);
        female.setBackground(Color.PINK);
        add(female);

        ButtonGroup buttonselect = new ButtonGroup();
        buttonselect.add(male);
        buttonselect.add(female);

        JLabel email = new JLabel("E-Mail :");
        email.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        email.setBounds(100, 340, 150, 30);
        add(email);

        emailtext = new JTextField();
        emailtext.setBounds(350, 340, 250, 30);
        emailtext.setFont(new Font("Arial", Font.BOLD, 15));
        add(emailtext);

        JLabel marital = new JLabel("Marital Status :");
        marital.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        marital.setBounds(100, 390, 150, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(350, 390, 80, 30);
        married.setBackground(Color.pink);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 90, 30);
        unmarried.setBackground(Color.PINK);
        add(unmarried);

        ButtonGroup maritalselect = new ButtonGroup();
        maritalselect.add(married);
        maritalselect.add(unmarried);

        JLabel address = new JLabel("Address :");
        address.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        address.setBounds(100, 440, 150, 30);
        add(address);

        addresstext = new JTextField();
        addresstext.setBounds(350, 440, 250, 30);
        addresstext.setFont(new Font("Arial", Font.BOLD, 15));
        add(addresstext);

        JLabel state = new JLabel("State :");
        state.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        state.setBounds(100, 490, 150, 30);
        add(state);

        statetext = new JTextField();
        statetext.setBounds(350, 490, 250, 30);
        statetext.setFont(new Font("Arial", Font.BOLD, 15));
        add(statetext);

        JLabel pin = new JLabel("Pin Code :");
        pin.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        pin.setBounds(100, 540, 150, 30);
        add(pin);

        pintext = new JTextField();
        pintext.setBounds(350, 540, 250, 30);
        pintext.setFont(new Font("Arial", Font.BOLD, 15));
        add(pintext);

        next = new JButton("NEXT");
        next.setBackground(Color.RED);
        next.setForeground(Color.black);
        next.setFont(new Font("Raleway", Font.BOLD, 16));
        next.setBounds(520, 590, 80, 30);
        next.addActionListener(this);
        add(next);

        back = new JButton("BACK");
        back.setBackground(Color.RED);
        back.setForeground(Color.black);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(420, 590, 80, 30);
        back.addActionListener(this);
        add(back);


        getContentPane().setBackground(Color.cyan);

        setSize(800, 700);
        setVisible(true);
        setLocation(350, 50);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nametext.getText();
        String fname = fnametext.getText();

        Date dobDate = datechoose.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dob = sdf.format(dobDate);

        try {
            LocalDate selectedDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate currentDate = LocalDate.now();

            if (selectedDate.isAfter(currentDate)) {
                JOptionPane.showMessageDialog(null, "The selected date cannot be later than the current date.");
                return; // Stop further processing
            } else {
                System.out.println("The selected date is valid.");
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format.");
            return; // Stop further processing
        }

        String gender = null;
        if (male.isSelected()) {
            gender = "male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = emailtext.getText();
        String maritalStatus = null;
        if (married.isSelected()) {
            maritalStatus = "married";
        } else if (unmarried.isSelected()) {
            maritalStatus = "unmarried";
        }

        String address = addresstext.getText();
        String state = statetext.getText();
        String pin = pintext.getText();

        // Create a JSON object to store the data
        JsonObject userData = new JsonObject();
        userData.addProperty("formno", formno);
        userData.addProperty("name", name);
        userData.addProperty("fname", fname);
        userData.addProperty("dob", dob);
        userData.addProperty("gender", gender);
        userData.addProperty("email", email);
        userData.addProperty("maritalStatus", maritalStatus);
        userData.addProperty("address", address);
        userData.addProperty("state", state);
        userData.addProperty("pin", pin);

        FileUtils.writeToFile(userData, "Signup.json");

        if (ae.getSource() == next) {
            // Validate that required fields are filled
            if (name.equals("") || dobDate == null || gender == null || email.equals("") || address.equals("") || state.equals("") || pin.equals("") || maritalStatus == null) {
                JOptionPane.showMessageDialog(null, "Please fill in all the required fields.");
            } else {
                // Proceed to the next signup step
                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }
        }

        if (ae.getSource() == back) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}