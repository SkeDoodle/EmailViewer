package Model;

import java.util.EventListener;

/**
 *
 * @author Thomas
 */
public interface OptionsListener extends EventListener {

    /**
     *
     * @param event
     */
    public void OptionsEventPerformed(OptionsEvent event);
}
