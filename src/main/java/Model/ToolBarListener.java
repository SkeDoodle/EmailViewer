package Model;

import java.util.EventListener;

/**
 *
 * @author Thomas
 */
public interface ToolBarListener extends EventListener {

    /**
     *
     * @param event
     */
    public void ToolBarButtonsEvent(ToolBarEvent event);
}
