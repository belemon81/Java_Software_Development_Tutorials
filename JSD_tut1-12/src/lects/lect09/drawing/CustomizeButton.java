package lects.lect09.drawing;

import javax.swing.*;
import java.awt.*;

public class CustomizeButton {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom JButton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JPanel body = new JPanel();
        JButton myCustomButton = new JButton("Hello") {
            @Override
            protected void paintComponent(Graphics g) {
                // super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawLine(5, 5, 10, 10);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // super.paintBorder(g);
            }
        };
        body.add(myCustomButton);
        frame.add(body);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
