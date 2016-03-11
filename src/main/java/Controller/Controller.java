package Controller;

import Model.Inbox;
import Model.OptionsEvent;
import Model.OptionsListener;
import Model.ToolBarEvent;
import View.View;
import Model.ToolBarListener;
import Model.TreeMenuEvent;
import Model.TreeMenuListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class Controller implements ToolBarListener, TreeMenuListener, OptionsListener {

    final private ArrayList<Inbox> inboxList;
    final private View view;
    private boolean treeMenuClicked;
    private String userName;

    /**
     *
     * @param inboxList
     * @param view
     */
    public Controller(ArrayList<Inbox> inboxList, View view) {
        this.inboxList = inboxList;
        this.view = view;

        this.inboxList.add(new Inbox("imap", "imap.gmail.com", "993", "address1@gmail.com", "password"));
        this.inboxList.add(new Inbox("imap", "imap.gmail.com", "993", "address2@gmail.com", "password"));
        this.view.getTreeMenu().addInbox(this.inboxList.get(0));
        this.view.getTreeMenu().addInbox(this.inboxList.get(1));
    }

    private void addInbox(ArrayList<Inbox> inboxList, String userName, String password) {

        //TODO filter username, for instance : 
        //if userName = john.doe@gmail.com, recognize "@gmail.com"
        //and generate adequate protocol, host and port
        //here "imap", "imap.gmail.com" and "993"
        //TODO check if connection can be made to the inbox
        inboxList.add(new Inbox("imap", "imap.gmail.com", "993", userName, password));
    }

    /* MAIN LOGIC OF THE APPLICATION */
    /*The Controller controls everything from here*/

    /**
     *
     * @param event
     */
    
    @Override
    public void ToolBarButtonsEvent(ToolBarEvent event) {

        switch (event.getValue()) {
            //Compose new mail logic
            case NewEmail:

                if (!this.inboxList.isEmpty()) {
                    String user = this.inboxList.get(0).getUserName();
                    String pass = this.inboxList.get(0).getPassword();
                    this.view.showNewMailPanel(user, pass);
                    this.view.getToolBar().deactivateNewMailBtn();
                } else {
                    JOptionPane.showMessageDialog(this.view, "You need to add an inbox first", "No inbox", JOptionPane.ERROR_MESSAGE);
                }

                break;

            //If the Option button is pressed, the controller starts listening to the option dialog events
            case Options:
                this.view.showOptionsPanel(this.inboxList);
                this.view.getOptionsDialog().addOptionsListener(this);

                break;

            //update mail list logic
            case CheckMails:

                if (this.treeMenuClicked) {
                    this.view.getToolBar().activateNewMailBtn();
                    this.view.showInbox(this.userName);
                }

                break;
            default:
                throw new AssertionError();
        }
    }

    //Left Tree menu logic,  works in pair with the toolbar buttons

    /**
     *
     * @param event
     */
        @Override
    public void treeMenuSelectedEvent(TreeMenuEvent event) {
        if (!event.getNode().toString().equals("Mails")) {
            this.treeMenuClicked = true;
            this.userName = event.getNode().toString();

        } else {
            this.treeMenuClicked = false;
            this.userName = "";
        }

    }

    //Option Dialog logic. Adding inbox and removing from the model
    //It also updates the left tree menu in real time

    /**
     *
     * @param event
     */
        @Override
    public void OptionsEventPerformed(OptionsEvent event) {

        if (event.getI() == 1) {

            this.inboxList.add(new Inbox("imap", "imap.gmail.com", "993", event.getUserName(), event.getPassword()));
            this.view.getTreeMenu().addInbox(this.inboxList.get(this.inboxList.size() - 1));
        } else if (event.getI() == 0) {

            for (int i = 0; i < this.inboxList.size(); i++) {

                if (this.inboxList.get(i).getUserName().equals(event.getUserName())) {
                    this.inboxList.remove(i);
                    this.view.getTreeMenu().removeInbox(event.getUserName());
                }
            }

        }

    }
}
