import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendEmail(String recipientEmail, String subject, String body) {
        // Sender's email and password (replace with your Gmail credentials)
        String senderEmail = "personalized.meal.app@gmail.com";
        String password = "Personalized@123";

        // SMTP server settings for Gmail
        String host = "smtp.gmail.com";
        int port = 587; // Port for TLS encryption
        String senderName = "Personalized Meal Service App";



        // Create properties for the SMTP session
        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true); // Enable STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");



        String username = "personalized.meal.app";
        String pass = "qoqymytysihozpev";

        //session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });

        try{
            Message message = new MimeMessage(session);

            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipientEmail));
            message.setFrom(new InternetAddress(senderEmail));

            message.setSubject(subject);

            message.setText(body);

            Transport.send(message);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
