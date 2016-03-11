package View;

import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Thomas
 */
public class OptionsView extends JDialog {

    private final EventListenerList optionsListenerList = new EventListenerList();

    private final JLabel adress;
    private final JLabel password;
    private final JPasswordField passwordText;
    private final JTextField userText;
    private JList list;
    private DefaultListModel dlm;
    private JButton addBtn;
    private JButton removeBtn;
    final private JFrame parent;
    final private ArrayList<Inbox> inboxList;

    /**
     *
     * @param parent
     * @param inboxList
     */
    public OptionsView(final JFrame parent, ArrayList<Inbox> inboxList) {

        this.setSize(parent.getWidth() / 2, parent.getHeight() / 2);
        this.setTitle("Options");
        this.parent = parent;
        this.setLocationRelativeTo(parent);

        this.inboxList = inboxList;

        this.list = new JList();
        this.dlm = new DefaultListModel();
        this.list.setModel(dlm);

        //If there is already something in the list
        if (!this.inboxList.isEmpty()) {
            for (Inbox inbox : inboxList) {
                this.dlm.addElement(inbox.getUserName());
            }
        }

        addBtn = new JButton("Add");
        removeBtn = new JButton("Delete");
        adress = new JLabel("User: ");
        password = new JLabel("Password: ");
        userText = new JTextField(20);
        passwordText = new JPasswordField(20);

        this.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight = 1;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5, 5, 5, 5);
        this.add(adress, layout);

        layout.gridx = 1;
        this.add(userText, layout);

        layout.gridx = 0;
        layout.gridy = 2;
        this.add(password, layout);

        layout.gridx = 1;
        this.add(passwordText, layout);

        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = GridBagConstraints.REMAINDER;
        this.add(listScroller, layout);

        layout.gridy = 4;
        layout.gridwidth = GridBagConstraints.REMAINDER;
        this.add(addBtn, layout);

        layout.gridy = 5;
        this.add(removeBtn, layout);

        /*   --    -- - -     LISTENERS   -  -   -   -  - */
        addBtn.addActionListener(new ActionListener() {

            //If the user adds an inbox
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (!passwordText.getText().isEmpty() && userText.getText() != null && ae.getSource() == addBtn) {
                    if (dlm.contains(userText.getText())) {
                        //TODO Warn the user that the Email address already exists WITHOUT a JOptionPane.showMessageDialog because it's too obstuctive
                    } else {
                        dlm.addElement(userText.getText());
                        fireOptionsEvent(new OptionsEvent(ae, 1, userText.getText(), passwordText.getText()));
                    }

                }
            }
        });
        //If the user removes an inbox
        removeBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == removeBtn && list.getSelectedIndex() != -1) {
                    dlm.remove(list.getSelectedIndex());
                    fireOptionsEvent(new OptionsEvent(ae, 0, userText.getText(), null));
                }

            }
        });
        //Updates the text fields with the selection of the list
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {

                if (!lse.getValueIsAdjusting() && list.getSelectedIndex() != -1) {
                    userText.setText(list.getSelectedValue().toString());
                    passwordText.setText("********");
                }

            }
        });

        this.setVisible(true);
    }
    
    
    // LISTENERS logic

    /**
     *
     * @param optionsListener
     */
        public void addOptionsListener(OptionsListener optionsListener) {
        this.optionsListenerList.add(OptionsListener.class, optionsListener);
    }

    /**
     *
     * @param optionsListener
     */
    public void removeOptionsListener(OptionsListener optionsListener) {
        this.optionsListenerList.remove(OptionsListener.class, optionsListener);
    }

    /**
     *
     * @param event
     */
    public void fireOptionsEvent(OptionsEvent event) {

        Object[] listeners = optionsListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == OptionsListener.class) {
                ((OptionsListener) listeners[i + 1]).OptionsEventPerformed(event);
            }
        }
    }

}
