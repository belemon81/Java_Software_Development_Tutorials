package lects.lect08.multitask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleClock {
    private static int counter;

    public static void main(String[] args) {
        JFrame w = new JFrame("Example");
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(300, 300);
        w.getContentPane().setLayout(new FlowLayout());

        JLabel clock = new JLabel("--");
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    counter++;
                    clock.setText(String.valueOf(counter));
                }
            }
        };
        JButton b = new JButton("Start");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
            }
        });
        w.add(b);
        w.add(clock);
        w.setVisible(true);
    }
}
