package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Thomas
 */
public class Menu extends JMenuBar {

    /**
     *
     */
    public Menu() {

        //File section
        JMenu file = new JMenu("File");
        //file.setMnemonic(KeyEvent.VK_F);

        //Exit item
        JMenuItem exitItem = new JMenuItem("Exit");
        //exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        file.add(exitItem);
        this.add(file);

        //Help section
        JMenu help = new JMenu("?");

        //About item
        JMenuItem aboutItem = new JMenuItem("About");

        help.add(aboutItem);
        this.add(help);

    }

}
