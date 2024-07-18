package lects.lect09.font;

import javax.swing.*;
import java.awt.*;

public class FontDrawing extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 350;
    public static final int X_START = 20;
    public static final int Y_START = 60;

    public FontDrawing() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Font Samples");

        getContentPane().setBackground(Color.white);
    }

    public static void main(String[] args) {
        FontDrawing gui = new FontDrawing();
        gui.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setFont(new Font("Serif", Font.PLAIN, 10));
        g.drawString("Serif, Plain, 10 Points", X_START, Y_START);

        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("SansSerif, Plain, 12 Points", X_START, Y_START + 30);

        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g.drawString("Monospaced, Plain, 14 Points", X_START, Y_START + 60);

        g.setFont(new Font("Serif", Font.ITALIC, 18));
        g.drawString("Serif, Italic, 18 Pts.", X_START, Y_START + 100);

        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        g.drawString("SansSerif, Bold, 24 Points", X_START, Y_START + 150);

        g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 32));
        g.drawString("Monospaced, Bold & Italic, 32 Points", X_START, Y_START + 200);

    }
}