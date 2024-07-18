package lects.lect08.scrollbar;

import lects.lect07.PanelDemo;
import lects.lect07.SimpleWindow;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanelDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(ScrollablePanelDemo.class.getName(), 300, 250);
        w.setLayout(new BorderLayout());

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 20);
        w.add(title, BorderLayout.NORTH);

        // create a detailed panel to put in the center
        JPanel subPanel = PanelDemo.createDetailedPanel(280, wz.height - 70);

        // put the detailed panel into another panel to protect its size
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(subPanel);

        /**
         * add the panel to a scroll bar
         */
        JScrollPane scrollBar = new JScrollPane(panel);

        /**
         * add scroll bar to window
         */
        w.add(scrollBar); // goes to CENTRE
        // create a button panel to put in the south
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new JButton("OK"));
        w.add(buttonPanel, BorderLayout.SOUTH);

        // Display the window.
        w.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
