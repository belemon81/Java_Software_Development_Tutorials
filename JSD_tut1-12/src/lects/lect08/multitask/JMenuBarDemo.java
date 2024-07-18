package lects.lect08.multitask;

import javax.swing.*;
import java.awt.*;

public class JMenuBarDemo {
    public static void main(String[] args) {
        JFrame w = new JFrame("Example");
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(300, 300);
        w.getContentPane().setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Exit"));

        JPanel panel = new JPanel();
        panel.add(new JLabel("This is some text"));
        menuBar.add(panel);
        menuBar.add(fileMenu);
        w.setJMenuBar(menuBar);
        w.add(menuBar);

        w.setVisible(true);
    }
}
