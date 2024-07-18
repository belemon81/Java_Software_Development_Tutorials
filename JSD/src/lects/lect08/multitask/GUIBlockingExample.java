package lects.lect08.multitask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBlockingExample {
    public static void main(String[] args) {
        JFrame w = new JFrame("Example");
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(300, 300);
        w.getContentPane().setLayout(new FlowLayout());
        JButton b = new JButton("A button");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            System.out.println("Hey");
                        }
                    }
                };
                t.start();
            }
        });
        w.add(b);
        w.setVisible(true);
    }
}
