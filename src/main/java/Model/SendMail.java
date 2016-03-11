package Model;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Sukru
 *
 */
public class SendMail {

    /**
     *
     * @param user
     * @param pass
     * @param smtpProperties
     * @param toAddress
     * @param subject
     * @param message
     * @throws AddressException
     * @throws MessagingException
     * @throws IOException
     */
    public static void sendEmail(String user, String pass, Properties smtpProperties, String toAddress,
            String subject, String message)
            throws AddressException, MessagingException, IOException {

        final String userName = user;
        final String password = pass;

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(smtpProperties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }
}
