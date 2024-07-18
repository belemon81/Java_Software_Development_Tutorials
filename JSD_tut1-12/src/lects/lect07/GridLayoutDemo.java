package lects.lect07;

import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(GridLayoutDemo.class.getName(), 300, 200);

        // use BorderLayout for the window
        w.setLayout(new BorderLayout());

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 20);
        w.add(title, BorderLayout.NORTH);

        // create a detailed panel to put in the center
        // use grid layout for the panel
        final int cellSpace = 3;
        JPanel panel = new JPanel(new GridLayout(2, 2, cellSpace, cellSpace));
        PanelDemo.createDetailedPanel(panel, wz.width - 20, wz.height - 100);
        w.add(panel, BorderLayout.CENTER);

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
