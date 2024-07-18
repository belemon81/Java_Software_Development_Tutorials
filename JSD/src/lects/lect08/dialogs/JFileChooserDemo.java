package lects.lect08.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChooserDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;

    private JFileChooser fileChooser;

    public JFileChooserDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("JFileChooser Demo");
        setSize(WIDTH, HEIGHT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout());
        JButton chooseButton = new JButton("Open file...");
        chooseButton.addActionListener(this);
        buttonPanel.add(chooseButton);
        chooseButton = new JButton("Save file...");
        chooseButton.addActionListener(this);
        buttonPanel.add(chooseButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // file chooser
        fileChooser = new JFileChooser();
    }

    public static void main(String[] args) {
        JFileChooserDemo gui = new JFileChooserDemo();
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int res = 0;
        if (cmd.equals("Open file...")) {
            res = fileChooser.showOpenDialog(this);
        } else if (cmd.equals("Save file...")) {
            res = fileChooser.showSaveDialog(this);
        }

        if (res == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            System.out.println("File: " + f);
        } else {
            System.out.println("Canceled...");
        }
    }
}
