package lects.lect09.toolkit;

import lects.lect07.PanelDemo;
import lects.lect07.SimpleWindow;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ToolkitDemo {
    private static void createAndShowGUI() {
        // get the window size
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int width = (int) (screenSize.width * 0.5);
        int height = (int) (screenSize.height * 0.5);

        // Create and set up the window.
        final JFrame w = new SimpleWindow(ToolkitDemo.class.getName(), width, height);
        w.setLayout(new BorderLayout());

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        // add an icon to the label as well
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 60);
        // create an image on a label
        URL imgURL = ToolkitDemo.class.getResource("../images/dukeWaveRed.gif");
        if (imgURL == null) {
            System.err.println("Could not find image");
        } else {
            Image img = tk.createImage(imgURL);
            ImageIcon ic = new ImageIcon(img);
            // can also do this:
            // ImageIcon ic = new ImageIcon(imgURL);
            title.setIcon(ic);
        }

        w.add(title, BorderLayout.NORTH);

        // create a detailed panel to put in the center
        JPanel subPanel = PanelDemo.createDetailedPanel(280, wz.height - 70);

        // put the detailed panel into another panel to protect its size
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(subPanel);

        JScrollPane scrollBar = new JScrollPane(panel);

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
        javax.swing.SwingUtilities.invokeLater(ToolkitDemo::createAndShowGUI);
    }
}
