package tutes.gui1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ColoredWindow extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public ColoredWindow(String title) {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // getContentPane().setBackground(theColor);
        // setBackground(theColor);

        JLabel aLabel = new JLabel("Close-window button works.");
        JButton button = new JButton("Change background");

        Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN,
                Color.YELLOW, Color.BLACK, Color.WHITE, Color.DARK_GRAY,
                Color.LIGHT_GRAY, Color.ORANGE, Color.MAGENTA, Color.GRAY};
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked!");
                // TODO: make color random
                // getContentPane().setBackground(Color.getHSBColor((float) (Math.random() * 255), (float) (Math.random() * 255), (float) (Math.random() * 255)));
                Random random = new Random();
                int index = random.nextInt(colors.length);
                Color color = colors[index];
                Color contrast;
                if (index == 12) contrast = colors[6];
                else if (index % 2 == 0) contrast = colors[index + 1];
                else contrast = colors[index - 1];

                getContentPane().setBackground(color);
                button.setBackground(contrast);
                aLabel.setForeground(contrast);
            }
        });
        add(button, BorderLayout.SOUTH);
        add(aLabel, BorderLayout.NORTH);
    }


//    public ColoredWindow(String title) {
//        this(title, Color.PINK);
//    }
}