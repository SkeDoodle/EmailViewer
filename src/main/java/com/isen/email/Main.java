package com.isen.email;

import Controller.Controller;
import Model.Inbox;
import View.View;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Thomas
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                runApp();
            }
        });

    }

    /**
     *
     */
    public static void runApp() {
        //Here we init the Model, view and controller
        ArrayList<Inbox> model = new ArrayList<>();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        //The controller subscribes to the Toolbar events and the TreeMenu events
        view.getToolBar().addToolBarListener(controller);
        view.getTreeMenu().addTreeMenuListener(controller);
    }
}
