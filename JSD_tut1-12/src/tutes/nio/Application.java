package tutes.nio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Application {
    JFrame gui;
    JTextArea history;
    JTextField field;
    JButton sendBtn;

    public Application() {
        gui = new JFrame(this.getClass().getSimpleName());
        gui.setSize(400, 300);
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        history = new JTextArea();
        history.setBorder(new EmptyBorder(0, 10, 0, 10));
        history.setEditable(false);
        history.setSize(400, 270);
        JScrollPane scrollPane = new JScrollPane(history);
        gui.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());

        field = new JTextField(27);
        sendBtn = new JButton("Send");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        bottom.add(field, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        bottom.add(sendBtn, constraints);
        gui.add(bottom, BorderLayout.SOUTH);
    }
}
