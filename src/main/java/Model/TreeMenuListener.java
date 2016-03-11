package Model;

import java.util.EventListener;

/**
 *
 * @author Thomas
 */
public interface TreeMenuListener extends EventListener {

    /**
     *
     * @param event
     */
    public void treeMenuSelectedEvent(TreeMenuEvent event);
}
