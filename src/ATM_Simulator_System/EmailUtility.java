package ATM_Simulator_System;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtility {
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587"; // TLS port for Gmail
    private static final String USERNAME = "skyadav98968@gmail.com";
    private static final String PASSWORD ="fkoi yhck ebjv xauc"; //"fyqlfwtekcjnity"; //"fyql fwte kcjnity";

    public static void sendEmail(String to, String subject, String body) {
        // Set up properties for the email server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Force TLS v1.2
        properties.put("mail.smtp.connectiontimeout", "10000"); // 10 seconds
        properties.put("mail.smtp.timeout", "10000"); // 10 seconds
        properties.put("mail.smtp.writetimeout", "10000"); // 10 seconds


        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace(); // Print full exception trace for debugging
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
