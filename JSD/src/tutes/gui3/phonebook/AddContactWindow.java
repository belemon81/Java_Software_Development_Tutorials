package tutes.gui3.phonebook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class AddContactWindow extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private JTable table;
    private JTextField nameField;
    private JTextField phoneField;

    public AddContactWindow(JTable table) {
        this.table = table;
        gui = new JFrame("Add Contact");
        gui.setSize(360, 150);
        gui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gui.setLocation(600, 300);

        JPanel input = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        input.setLayout(gridLayout);
        input.setBorder(new EmptyBorder(10, 20, 10, 20));
        JLabel name = new JLabel("Name");
        JLabel phone = new JLabel("Phone");
        nameField = new JTextField();
        phoneField = new JTextField();
        name.setLabelFor(nameField);
        name.setDisplayedMnemonic('n');
        phone.setLabelFor(phoneField);
        phone.setDisplayedMnemonic('p');

        input.add(name);
        input.add(nameField);
        input.add(phone);
        input.add(phoneField);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        JButton addContact = new JButton("Add Contact");
        JButton cancel = new JButton("Cancel");
        addContact.addActionListener(this);
        cancel.addActionListener(this);
        buttons.add(addContact);
        buttons.add(cancel);

        gui.add(buttons, BorderLayout.SOUTH);
        gui.add(input, BorderLayout.CENTER);
        gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Contact")) {
            DefaultTableModel tm = (DefaultTableModel) table.getModel();
            tm.addRow(new String[]{
                    nameField.getText(),
                    phoneField.getText()
            });
            gui.dispose();
        } else if (e.getActionCommand().equals("Cancel")) {
            gui.dispose();
        }
    }
}
