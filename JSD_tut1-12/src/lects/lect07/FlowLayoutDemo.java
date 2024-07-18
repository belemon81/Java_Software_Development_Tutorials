package lects.lect07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowLayoutDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(FlowLayoutDemo.class.getSimpleName(), 290, 200);

        final Dimension wz = w.getSize();

        // Create a panel
        final JPanel panel = PanelDemo.createDetailedPanel(
                wz.width - 20,
                wz.height - 100);
        w.add(panel);
        panel.validate();

        // a button to change orientation
        final JButton button = new JButton("Orientation [L->R]");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // change component orientation on both the content pane and the panel
                // must invoke getContentPane()...
                Container contentPane = w.getContentPane();
                if (contentPane.getComponentOrientation().isLeftToRight()) {
                    contentPane
                            .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    button.setText("Orientation [R->L]");
                } else {
                    contentPane
                            .setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    button.setText("Orientation [L->R]");
                }

                contentPane.validate();
            }
        });
        w.add(button);

        // Display the window.
        //w.pack();
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
