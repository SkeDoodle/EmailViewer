package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author Thomas
 */
public class Inbox {

    private String protocol;
    private String host;
    private String port;
    private String userName;
    private String password;

    /**
     *
     */
    public ArrayList<Mail> mailList;

    /**
     *
     * @param protocol
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public Inbox(String protocol, String host, String port, String userName, String password) {

        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.mailList = new ArrayList();
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @return
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     *
     * @return
     */
    public String getPort() {
        return port;
    }

    private Properties getServerProperties(String protocol, String host, String port) {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));

        return properties;
    }

    /**
     *
     */
    public void getEmails() {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(getUserName(), password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();
            int numberOfMailsToDisplay;

            //If the inbox contains more than 40 mails, we only display the first 40 ones
            if (messages.length >= 40) {
                numberOfMailsToDisplay = 39;
            } else {
                numberOfMailsToDisplay = messages.length;
            }

            for (int i = numberOfMailsToDisplay - 1; i >= 0; i--) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                if (from.length() > 30) {
                    from = from.substring(0, 30);
                }
                if (from.contains("<")) {
                    from = from.substring(0, from.indexOf("<"));
                }

                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                Date sentDate = msg.getSentDate();

                String contentType = msg.getContentType();
                String messageContent = "";

                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (IOException | MessagingException ex) {
                        messageContent = "[Error downloading content]";
                    }
                }

                mailList.add(new Mail(from, subject, sentDate));

            }

            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
        }
    }

    /**
     * Returns a list of addresses in String format separated by comma
     *
     * @param address an array of Address objects
     * @return a string represents a list of addresses
     */
    private String parseAddresses(Address[] address) {
        String listAddress = "";

        if (address != null) {
            for (Address addres : address) {
                listAddress += addres.toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }

        return listAddress;
    }
}
