package View;

import Controller.Panels;
import Model.ToolBarEvent;
import Model.ToolBarListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.EventListenerList;

/**
 *
 * @author isen
 */
public class ToolBarView extends JToolBar {

    final private JButton newMailBtn;
    final private EventListenerList toolBarListenerList = new EventListenerList();

    /**
     *
     */
    public ToolBarView() {

        this.setFloatable(false);

        /*
         *   Here are some more cool icons :   http://findicons.com/pack/770/token_dark
         *   I chose the 32x32 .png format
         */
        JButton checkMailBtn = new JButton("Get Mails", new ImageIcon("img/mail.png"));
        newMailBtn = new JButton("Compose", new ImageIcon("img/writing.png"));
        JButton optionsBtn = new JButton(new ImageIcon("img/gear.png"));

        this.add(checkMailBtn);
        this.add(newMailBtn);
        this.add(optionsBtn);

        /*   --    -- - -     LISTENERS   -  -   -   -  - */
        checkMailBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fireToolBarEvent(new ToolBarEvent(ae, Panels.CheckMails));
            }
        });
        optionsBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fireToolBarEvent(new ToolBarEvent(ae, Panels.Options));
            }
        });
        newMailBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fireToolBarEvent(new ToolBarEvent(ae, Panels.NewEmail));
            }
        });
    }

    /**
     *
     * @param toolBarListener
     */
    public void addToolBarListener(ToolBarListener toolBarListener) {
        this.toolBarListenerList.add(ToolBarListener.class, toolBarListener);
    }

    /**
     *
     * @param toolBarListener
     */
    public void removeToolBarListener(ToolBarListener toolBarListener) {
        this.toolBarListenerList.remove(ToolBarListener.class, toolBarListener);
    }

    private void fireToolBarEvent(ToolBarEvent event) {
        Object[] listeners = toolBarListenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ToolBarListener.class) {
                ((ToolBarListener) listeners[i + 1]).ToolBarButtonsEvent(event);
            }
        }
    }

    /**
     *
     */
    public void deactivateNewMailBtn() {
        this.newMailBtn.setEnabled(false);
    }

    /**
     *
     */
    public void activateNewMailBtn() {
        this.newMailBtn.setEnabled(true);
    }
}
