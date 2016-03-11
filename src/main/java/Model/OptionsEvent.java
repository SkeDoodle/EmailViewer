package Model;

import java.util.EventObject;

/**
 *
 * @author Thomas
 */
public class OptionsEvent extends EventObject {

    final private int i;
    final private String userName;
    final private String password;

    /**
     *
     * @param o
     * @param i
     * @param userName
     * @param password
     */
    public OptionsEvent(Object o, int i, String userName, String password) {
        super(o);
        this.i = i;
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

}
