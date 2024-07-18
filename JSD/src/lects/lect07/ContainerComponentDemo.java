package lects.lect07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerComponentDemo {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new ContainerComponentDemo()).createAndShowGUI();
            }
        });
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(ContainerComponentDemo.class.getName(), 300, 200);

        // use BorderLayout for the window
        w.setLayout(new BorderLayout());

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 20);
        w.add(title, BorderLayout.NORTH);

        // Create a panel to add to centre
        final JPanel p = PanelDemo.createDetailedPanel(wz.width - 20, wz.height - 100);
        w.add(p);   // default: CENTER

        // a button to change orientation
        final JButton button = new JButton("Component OFF");
        button.setForeground(Color.BLUE);
        button.addActionListener(new ActionListener() {
            private int index = 0;
            private boolean last = true;

            public void actionPerformed(ActionEvent e) {
                // find the next component on the display
                Component[] comps = p.getComponents();

                // get the current component
                Component comp = comps[index];
                String type = comp.getClass().getSimpleName();
                System.out.printf("Component %d: %s%n", index, type);

                // switch visibility to on/off
                if (comp.isVisible()) {
                    comp.setVisible(false);
                    if (last) last = false;
                } else {
                    comp.setVisible(true);
                    if (!last) last = true;
                }
                p.validate();
                // p.invalidate();

                index++;

                // cycle back to first if already processed all components
                if (index > comps.length - 1) {
                    index = 0;
                    if (last)
                        button.setText("Component OFF");
                    else
                        button.setText("Component ON");
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button);
        w.add(buttonPanel, BorderLayout.SOUTH);

        // Display the window.
        w.setVisible(true);
    }
}
