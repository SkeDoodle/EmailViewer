package View;

import Controller.Output;
import Model.SendMail;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.*;

/**
 *
 * @author Sukru
 *
 */
public class SendMenu extends JPanel {

    Output config = new Output();
    JButton send;
    JLabel receiver, object;
    JTextField receiverText, objectText;
    JTextArea textAreaMessage;
    GridBagConstraints layout;
    JScrollPane scroll;

    /**
     *
     * @param userName
     * @param password
     */
    public SendMenu(String userName, String password) {

        //Initialisation des composants
        receiver = new JLabel("To : ");
        receiverText = new JTextField(30);
        object = new JLabel("Object : ");
        objectText = new JTextField(30);
        textAreaMessage = new JTextArea(10, 30);
        scroll = new JScrollPane(textAreaMessage);
        send = new JButton("SEND");

        //Initialisation de Layout
        this.setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.insets = new Insets(5, 5, 5, 5);

        //Positionnement des composants
        //Label "receiver"
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight = 1;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.HORIZONTAL;
        this.add(receiver, layout);

        //Text Field "receiverText"
        layout.gridx = 1;
        layout.gridwidth = GridBagConstraints.REMAINDER;
        this.add(receiverText, layout);

        //Label "object"
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 1;
        this.add(object, layout);

        //Text Field "objectText"
        layout.gridx = 1;
        layout.gridwidth = GridBagConstraints.REMAINDER;
        this.add(objectText, layout);

        //Text Area "textAreaMessage"
        layout.gridx = 0;
        layout.gridy = 2;
        this.add(scroll, layout);

        //Bouton "send"
        layout.gridy = 3;
        this.add(send, layout);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    buttonSendActionPerformed(event);
                } catch (IOException ex) {
                    Logger.getLogger(SendMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void buttonSendActionPerformed(ActionEvent event) throws IOException {

        String ToAddress = receiverText.getText();
        String subject = objectText.getText();
        String message = textAreaMessage.getText();

        try {
            Properties smtpProperties = config.loadProperties();
            SendMail.sendEmail("testmailviewer@gmail.com", "pommePoire", smtpProperties, ToAddress, subject, message);
            JOptionPane.showMessageDialog(this, "Your mail has been sent");

        } catch (MessagingException | HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Couldn't send the mail: "
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
