package View;

import Model.Inbox;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author Thomas
 */
public class View extends JFrame {

    final private ArrayList<Inbox> inboxList;
    private final ToolBarView toolbar;
    private final JScrollPane scrollPanel;
    final private TreeMenuView treeMenu;
    private OptionsView options;

    /*THE MAIN VIEW*/

    /**
     *
     * @param inboxList
     */
    
    public View(ArrayList<Inbox> inboxList) {

        this.inboxList = inboxList;
        this.toolbar = new ToolBarView();
        this.treeMenu = new TreeMenuView();

        this.setTitle("Email Viewer");
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds the top menu
        this.setJMenuBar(new Menu());

        //Adds the toolbar
        this.getContentPane().add(this.toolbar, BorderLayout.NORTH);

        //Creates the left menu and the main Panel
        this.scrollPanel = new JScrollPane();
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.treeMenu, scrollPanel);

        //Adds the main panel
        this.getContentPane().add(splitPane);

        this.setVisible(true);
    }

    /**
     *
     * @return
     */
    public ToolBarView getToolBar() {
        return this.toolbar;
    }

    /**
     *
     * @return
     */
    public TreeMenuView getTreeMenu() {
        return this.treeMenu;
    }

    /**
     *
     * @return
     */
    public OptionsView getOptionsDialog() {
        return this.options;
    }

    //shows the inbox on the main Frame

    /**
     *
     * @param userName
     */
        public void showInbox(String userName) {
        for (Inbox inbox : inboxList) {
            if (inbox.getUserName().equals(userName)) {
                this.scrollPanel.setViewportView(new InboxView(inbox));
            }
        }

    }

    //Displays the New Mail Panel

    /**
     *
     * @param userName
     * @param password
     */
        public void showNewMailPanel(String userName, String password) {
        this.scrollPanel.setViewportView(new SendMenu(userName, password));
    }

    //Displays the Option Dialog

    /**
     *
     * @param inboxList
     */
        public void showOptionsPanel(ArrayList<Inbox> inboxList) {
        this.options = new OptionsView(this, inboxList);
    }
}
