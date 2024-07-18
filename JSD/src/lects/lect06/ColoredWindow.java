package lects.lect06;

import javax.swing.*;
import java.awt.*;

public class ColoredWindow extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public ColoredWindow(String title, Color theColor) {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(theColor);
        //setBackground(theColor);

        JLabel aLabel = new JLabel("Close-window button works.");
        add(aLabel, BorderLayout.NORTH);
    }

    public ColoredWindow(String title) {
        this(title, Color.PINK);
    }
}
