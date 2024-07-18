package lects.lect07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(ComboBoxDemo.class.getName(), 300, 200);
        w.setLayout(new BorderLayout());

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 20);
        w.add(title, BorderLayout.NORTH);

        // create a detailed panel to put in the center
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // first create a combo box at the top
        createComboBox(panel);
        // then other labels and text fields
        PanelDemo.createDetailedPanel(panel, wz.width - 20, wz.height - 100);
        w.add(panel, BorderLayout.CENTER);

        // create a button panel to put in the south
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new JButton("OK"));
        w.add(buttonPanel, BorderLayout.SOUTH);

        // Display the window.
        w.setVisible(true);
    }

    // add a combo box to the panel
    public static void createComboBox(final JPanel panel) {
        String[] cities = {"", "Hà nội", "TP Hcm", "Đà nẵng", "Huế", "Thái nguyên"};

        JLabel cityLabel = new JLabel("Choose a city:");
        cityLabel.setPreferredSize(new Dimension(100, 20));

        // Create the combo box, select the item at index 0.
        // Indices start at 0, so 4 specifies the pig.
        JComboBox cityList = new JComboBox(cities);
        cityList.setSelectedIndex(1);
        // handle combo box event
        cityList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String city = (String) cb.getSelectedItem();
                // update the address text field
                updateAddress(panel, city);
            }
        });
        // ALTERNATIVE: handle item state change event
        cityList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String city = (String) cb.getSelectedItem();
                    // update the address text field
                    updateAddress(panel, city);
                }
            }
        });

        cityLabel.setLabelFor(cityList);
        panel.add(cityLabel);
        panel.add(cityList);
    }

    // set the address text field to city
    public static void updateAddress(JPanel panel, String city) {
        Component[] comps = panel.getComponents();
        JLabel label;
        JTextField tf;
        for (Component comp : comps) {
            if (comp instanceof JLabel) {
                label = (JLabel) comp;
                if (label.getText().equals("address:")) {
                    tf = (JTextField) label.getLabelFor();
                    tf.setText(city);
                    break;
                }
            }
        }
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
