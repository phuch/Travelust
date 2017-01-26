/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Date;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ADMIN
 */
@Stateless
@LocalBean
public class EmailSessionBean {
    private String port = "587";
    private String host = "smtp.gmail.com";
    private String from = "testingemail607@gmail.com";
    private boolean auth = true;
    private String username = "testingemail607@gmail.com";
    private String password = "adminlongdoptrai";
    private boolean debug = true;
    
    public void sendMail(String name, String to, String subject){
        //Settings for SMTP protocol
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", true);
//        switch (protocol) {
//            case SMTPS:
//                props.put("mail.smtp.ssl.enable", true);
//                break;
//            case TLS:
//                props.put("mail.smtp.starttls.enable", true);
//                break;
//        }
        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
        //Session instance using the Properties object and the Authenticator object
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            //InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setSentDate(new Date());
            //Set email content
            Multipart multipart = new MimeMultipart("alternative");
            //Alternative text
            MimeBodyPart textPart = new MimeBodyPart();
            String textContent = "If you're seeing this content, that means either your browser or mail does not support HTML form\n"
                                + "Please copy the following link into your browser to activate account:\n"
                                + "http://10.114.32.23:8080/Travelust/webresources/service/verify?email=" + to;
            textPart.setText(textContent);
            //HTML part
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html>"
                                + "<h1>Hi " + name + ",</h1>"
                                + "<p>Welcome to Travelust</p>"
                                + "<p>Please click the link below to complete your sign up</p>"
                                + "<p><a href=\"http://10.114.32.23:8080/Travelust/webresources/service/verify?email=" + to + "\">Verify Link</a></p>"
                                + "</html>";
            htmlPart.setContent(htmlContent, "text/html");
            //Add Parts
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            //Send message
            Transport.send(message);
            System.out.println("sent to" + to);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
