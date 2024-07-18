package lects.lect09.font;

import javax.swing.*;
import java.awt.*;

public class FontDemo {

    public static void main(String[] args) {
        FontDemo gui = new FontDemo();
        gui.createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Font samples");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // some fonts
        Font[] fonts = { //
                new Font("Serif", Font.PLAIN, 10), //
                new Font("SansSerif", Font.PLAIN, 12), //
                new Font("Monospaced", Font.PLAIN, 16), //
                new Font("Serif", Font.ITALIC, 18), //
                new Font("SansSerif", Font.BOLD, 24), //
                new Font("Monospaced", Font.BOLD | Font.ITALIC, 32), //
        };

        // create labels for the fonts
        JLabel label;
        JLabel pLabels = new JLabel();
        pLabels.setLayout(new GridLayout(fonts.length, 1));

        for (Font f : fonts) {
            label = new JLabel(f.getFontName() + ", " + f.getSize() + " points");
            label.setFont(f);
            pLabels.add(label);
        }

        frame.add(pLabels);

        frame.setSize(700, 300);
        frame.setLocation(100, 100);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
    }
}