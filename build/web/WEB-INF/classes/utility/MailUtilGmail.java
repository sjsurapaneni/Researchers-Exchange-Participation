/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Vinayak
 */
public class MailUtilGmail {

    public static void sendMail(String to, String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        
        String from = "researcherexchange.nbad";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ehlo","true");
        properties.put("mail.smtp.connectiontimeout","40000");
        properties.put("mail.smtp.timeout","40000");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("researcherexchange.nbad", "researcherexchange");
            }
        });
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setSubject(subject);

        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        
        Transport transport = session.getTransport("smtp");
        transport.connect();
        try {
            transport.sendMessage(message, message.getAllRecipients());
            //Transport.send(message);
        } catch (MessagingException e) {
            System.err.println(e.toString());
        } 
        finally {
            transport.close();
        }

    }
}
