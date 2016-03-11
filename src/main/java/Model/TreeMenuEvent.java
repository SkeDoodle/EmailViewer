package Model;

import java.util.EventObject;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Thomas
 */
public class TreeMenuEvent extends EventObject {

    final private DefaultMutableTreeNode node;

    /**
     *
     * @param o
     * @param node
     */
    public TreeMenuEvent(Object o, DefaultMutableTreeNode node) {
        super(o);
        this.node = node;
    }

    /**
     *
     * @return
     */
    public DefaultMutableTreeNode getNode() {
        return node;
    }

}
