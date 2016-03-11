package View;

import Model.TreeMenuListener;
import Model.TreeMenuEvent;
import Model.Inbox;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Thomas
 */
public class TreeMenuView extends JPanel {

    private EventListenerList treeMenuListenerList = new EventListenerList();

    private JTree tree;
    private DefaultMutableTreeNode top;

    /**
     *
     */
    public TreeMenuView() {
        top = new DefaultMutableTreeNode("Mails");
        tree = new JTree(top);
        //removes the background color
        tree.setBackground(null);
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            //remove the background color of unselected nodes
            @Override
            public Color getBackgroundNonSelectionColor() {
                return null;
            }
        });

        /* - - - - - - - LISTENERS - - - - - -*/
        this.tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();

                fireTreeMenuEvent(new TreeMenuEvent(e, node));
            }
        });
        this.add(tree);
    }

    /**
     *
     * @param listener
     */
    public void addTreeMenuListener(TreeMenuListener listener) {
        this.treeMenuListenerList.add(TreeMenuListener.class, listener);
    }

    /**
     *
     * @param listener
     */
    public void removeTreeMenuListener(TreeMenuListener listener) {
        this.treeMenuListenerList.remove(TreeMenuListener.class, listener);
    }

    private void fireTreeMenuEvent(TreeMenuEvent event) {
        Object[] listeners = treeMenuListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TreeMenuListener.class) {
                ((TreeMenuListener) listeners[i + 1]).treeMenuSelectedEvent(event);
            }
        }
    }

    //Updates the JTree with a new inbox

    /**
     *
     * @param inbox
     */
        public void addInbox(Inbox inbox) {

        DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        model.insertNodeInto(new DefaultMutableTreeNode(inbox.getUserName()), root, root.getChildCount());
        this.expandToLast(tree);
    }

    //Updates the JTree by deleting an Inbox

    /**
     *
     * @param userName
     */
        public void removeInbox(String userName) {
        DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(userName)) {
                TreePath path = new TreePath(node.getPath());
                node = (DefaultMutableTreeNode) path.getLastPathComponent();
                model.removeNodeFromParent(node);
            }
        }

    }

    //expands the JTree

    private void expandAll(JTree tree) {
        int row = 0;
        while (row < tree.getRowCount()) {
            tree.expandRow(row);
            row++;
        }
    }

    // expand to the last leaf from the root
    private void expandToLast(JTree tree) {

        DefaultMutableTreeNode root;
        root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        tree.scrollPathToVisible(new TreePath(root.getLastLeaf().getPath()));
    }

}
