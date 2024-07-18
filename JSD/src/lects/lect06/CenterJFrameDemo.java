package lects.lect06;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenterJFrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("I'm centered");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(480, 270);
        JButton b = new JButton("Click me");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getModifiers());
                System.out.println(e.getWhen());
                System.out.println(e.paramString());
            }
        });
        frame.add(b);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
