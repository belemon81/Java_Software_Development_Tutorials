package lects.lect07;

import javax.swing.*;
import java.awt.*;

public class PanelDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame w = new SimpleWindow(PanelDemo.class.getName());

        final Dimension wz = w.getSize();

        // Create a top-level panel
        JPanel p = new JPanel();
        p.setPreferredSize((Dimension) wz.clone());
        p.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.BLUE));

        // create a title to add to the top panel
        JLabel title = createLabel("Enter details", wz.width - 20, 20);
        p.add(title);

        // create a sub-panel to add to the top panel
        JPanel subPanel = createDetailedPanel(wz.width - 20, wz.height - 50);
        p.add(subPanel);

        // add top panel to window
        w.add(p);

        // pack window
        w.pack();

        // display the window
        w.setVisible(true);
    }

    public static JLabel createLabel(String text, int width, int height) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(Color.YELLOW);
        label.setForeground(Color.BLUE);
        label.setPreferredSize(new Dimension(width, height));
        return label;
    }

    public static void createDetailedPanel(JPanel panel, int width, int length) {
        panel.setPreferredSize(new Dimension(width, length));
        panel.setBorder(BorderFactory.createEtchedBorder());
        String[] labels = {"name:", "address:"};
        char[] mnemonics = {'n', 'a'};
        final int labelWidth = 100;
        final int labelHeight = 20;

        // labels and texts
        JLabel label;
        JTextField tf;
        int index = 0;
        for (String lbl : labels) {
            // the text field
            tf = new JTextField(15);

            // label
            label = new JLabel(lbl);
            label.setLabelFor(tf);
            label.setPreferredSize(new Dimension(labelWidth, labelHeight));
            label.setDisplayedMnemonic(mnemonics[index]);

            // add to window
            panel.add(label);
            panel.add(tf);
            index++;
        }
    }

    public static JPanel createDetailedPanel(int width, int length) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createDetailedPanel(panel, width, length);

        return panel;
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
