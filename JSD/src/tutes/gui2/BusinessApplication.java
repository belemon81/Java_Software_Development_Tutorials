package tutes.gui2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BusinessApplication {
    public static void main(String[] args) {
        JFrame window = new JFrame("Ex2: Business Application");
        window.setSize(400, 200);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        JMenuItem menuItem = new JMenuItem("File");

        // title
        JPanel title = new JPanel();
        title.setBackground(Color.YELLOW);
        JLabel text = new JLabel("Welcome to Java");

        // fields
        JPanel fields = new JPanel();
        fields.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel name = new JLabel("name");
        JLabel dob = new JLabel("date of birth");
        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField();
        name.setLabelFor(nameField);
        name.setDisplayedMnemonic('n');
        dob.setLabelFor(dobField);
        dob.setDisplayedMnemonic('d');

        // buttons
        JPanel buttons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        // add components
        menuBar.add(menuItem);

        title.add(text);

        fields.setLayout(new GridLayout(2, 2));
        fields.add(name);
        fields.add(nameField);
        fields.add(dob);
        fields.add(dobField);

        buttons.setLayout(new FlowLayout());
        buttons.add(btnOk);
        buttons.add(btnCancel);

        window.setJMenuBar(menuBar);
        window.add(title, BorderLayout.NORTH);
        window.add(buttons, BorderLayout.SOUTH);
        window.add(fields, BorderLayout.CENTER);

        window.setVisible(true);
    }
}
