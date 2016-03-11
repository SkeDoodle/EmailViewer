package View;

import Controller.Output;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Sukru
 *
 */
public class NewAdressMenu extends JPanel {

    Output config = new Output();
    JLabel adress, password;
    JTextField userText;
    JPasswordField passwordText;
    JButton save;
    GridBagConstraints layout;

    /**
     *
     * @param config
     * @throws IOException
     */
    public NewAdressMenu(Output config) throws IOException {

        this.config = config;

        //Initialisation des composants
        adress = new JLabel("User: ");
        password = new JLabel("Password: ");
        userText = new JTextField(20);
        passwordText = new JPasswordField(20);
        save = new JButton("SAVE");

        //Initialisation de layout
        this.setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.insets = new Insets(10, 10, 5, 10);

        //Positionnement des composants
        //Label "adress"
        layout.gridx = 0;
        layout.gridy = 2;
        layout.anchor = GridBagConstraints.WEST;
        this.add(adress, layout);

        //Text Field "userText"
        layout.gridx = 1;
        this.add(userText, layout);

        //Label "password"
        layout.gridx = 0;
        layout.gridy = 3;
        this.add(password, layout);

        //Text Field "passwordText"
        layout.gridx = 1;
        this.add(passwordText, layout);

        //Bouton "save"
        layout.gridx = 0;
        layout.gridy = 4;
        layout.gridwidth = 2;
        layout.anchor = GridBagConstraints.EAST;
        this.add(save, layout);

        // Bouton activé pour tester le fichier de sauvgarde, méthode à placer dans le controleur
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    buttonSaveActionPerformed(event);
                } catch (IOException ex) {
                    Logger.getLogger(NewAdressMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        loadSettings();

    }

    private void loadSettings() throws IOException {
        Properties configProps = null;
        configProps = config.loadProperties();
        userText.setText(configProps.getProperty("mail.user"));
        passwordText.setText(configProps.getProperty("mail.password"));
    }

    private void buttonSaveActionPerformed(ActionEvent event) throws IOException {

        config.saveProperties("smtp.gmail.com", "587", userText.getText(), passwordText.getText());
        JOptionPane.showMessageDialog(NewAdressMenu.this, "Modifications sauvgardés avec succés!");

    }
}
