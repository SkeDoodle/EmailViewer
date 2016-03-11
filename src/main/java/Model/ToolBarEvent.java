package Model;

import Controller.Panels;
import java.util.EventObject;

/**
 *
 * @author Thomas
 */
public class ToolBarEvent extends EventObject {

    final private Panels value;

    /**
     *
     * @param o
     * @param value
     */
    public ToolBarEvent(Object o, Panels value) {
        super(o);
        this.value = value;
    }

    /**
     *
     * @return
     */
    public Panels getValue() {
        return value;
    }
}
