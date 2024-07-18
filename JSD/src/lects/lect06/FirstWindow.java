package lects.lect06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame {
    // default width and length of the window
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public FirstWindow() {
        super();
        setSize(WIDTH, HEIGHT);

        setTitle("First Window Class");

        // handle window closing event
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a button
        JButton endButton = new JButton("Click to end program.");

        // handle action event of the button
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // add button to default position on window (centre)
        add(endButton);
        // add button to the top of the window
        add(endButton, BorderLayout.NORTH);
    }
}
