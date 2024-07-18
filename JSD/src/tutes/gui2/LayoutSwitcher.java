package tutes.gui2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutSwitcher {
    private static int currentLayout = 0;

    public static void main(String[] args) {
        // data
        JLabel label1 = new JLabel("Label 1");
        label1.setHorizontalAlignment(JLabel.CENTER);
        JLabel label2 = new JLabel("Label 2");
        label2.setHorizontalAlignment(JLabel.CENTER);

        JComponent[] components = new JComponent[]{
                label1, new JButton("Button 1"),
                label2, new JButton("Button 2")

        };

        LayoutManager[] layouts = new LayoutManager[]{
                new FlowLayout(),
                new BorderLayout(),
                new GridLayout(2, 2)
        };

        String[] regions = new String[]{
                BorderLayout.NORTH,
                BorderLayout.EAST,
                BorderLayout.SOUTH,
                BorderLayout.WEST,
        };

        // create a window
        JFrame window = new JFrame("Exercise 1: Layout Switcher");
        window.setSize(300, 300);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        // create main area and switch button
        JPanel main = new JPanel();
        JButton switchBtn = new JButton("Switch layout...");
        window.add(main, BorderLayout.CENTER);
        window.add(switchBtn, BorderLayout.SOUTH);

        for (Component component : components) {
            main.add(component);
        }

        // enable switching between layout
        switchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLayout = (currentLayout + 1) % layouts.length;
                main.setLayout(layouts[currentLayout]);
                System.out.println("Current layout: #" + currentLayout);
                if (layouts[currentLayout] instanceof BorderLayout) {
                    // add components with region
                    for (int i = 0; i < components.length; i++) {
                        main.add(components[i], regions[i]);
                    }
                }
                // refresh
                main.validate();
            }
        });


        // set visible
        window.setVisible(true);
    }
}
