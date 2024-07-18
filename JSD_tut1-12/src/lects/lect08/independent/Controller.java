package lects.lect08.independent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author dmle
 * @overview Represents the controller component, which handles the user interaction
 * on the <tt>Gui</tt>
 */
public class Controller extends WindowAdapter implements ActionListener {
    private Gui gui;

    /**
     * @effects sets <tt>this.gui = gui</tt>
     */
    public void setGui(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Ok")) {
            processInput();
        } else if (cmd.equals("Cancel")) {
            clearInput();
        } else if (cmd.equals("Exit")) {
            shutDown();
        } // add other cases here
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shutDown();
    }

    /**
     * @effects read user input on <tt>gui</tt> and process them
     */
    private void processInput() {
        String t1 = gui.getName();
        String t2 = gui.getAddress();
        // process input here
        System.out.printf("Name: %s%nAddress: %s%n", t1, t2);
    }

    /**
     * @effects clear the text fields on <tt>gui</tt>
     */
    private void clearInput() {
        gui.clear();
    }

    /**
     * Shut down this application.
     *
     * @effects shutdown <tt>gui</tt> and exit.
     */
    private void shutDown() {
        gui.shutDown();
        System.exit(0);
    }
}
