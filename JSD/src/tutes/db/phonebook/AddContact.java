package tutes.db.phonebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddContact extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private JFrame parentGUI;
    private PhoneBook phoneBook;
    private JTextField txtName;
    private JTextField txtPhone;

    public AddContact(JFrame parentGUI, PhoneBook phoneBook) {
        this.parentGUI = parentGUI;
        this.phoneBook = phoneBook;
        createGUI();
    }

    private void createGUI() {
        gui = new JFrame("Add Contact");
        gui.addWindowListener(this);

        // center panel
        JPanel pnlMiddle = new JPanel(new GridLayout(2, 2, 5, 10));
        pnlMiddle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        pnlMiddle.add(new JLabel("Name"));
        txtName = new JTextField(15);
        pnlMiddle.add(txtName);
        pnlMiddle.add(new JLabel("Phone"));
        txtPhone = new JTextField(15);
        pnlMiddle.add(txtPhone);

        gui.add(pnlMiddle);

        // bottom
        JPanel pnlBottom = new JPanel();

        JButton btnAddContact = new JButton("Add Contact");
        btnAddContact.addActionListener(this);
        pnlBottom.add(btnAddContact);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        pnlBottom.add(btnCancel);

        pnlBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        gui.add(pnlBottom, BorderLayout.SOUTH);
        gui.pack();

        int x = (int) parentGUI.getLocation().getX() + 100;
        int y = (int) parentGUI.getLocation().getY() + 100;
        gui.setLocation(x, y);
    }

    public void display() {
        gui.setVisible(true);
        System.out.println("Add Contact GUI displayed...");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        disposeGUI();
    }

    private void disposeGUI() {
        txtName.setText("");
        txtPhone.setText("");
        gui.dispose();
        System.out.println("Add Contact GUI disposed...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Cancel")) {
            disposeGUI();
        } else if (command.equals("Add Contact")) {
            phoneBook.addContact(txtName.getText(), txtPhone.getText());
            disposeGUI();
        }
    }
}
