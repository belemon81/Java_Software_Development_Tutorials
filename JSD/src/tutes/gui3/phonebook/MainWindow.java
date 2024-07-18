package tutes.gui3.phonebook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MainWindow extends WindowAdapter implements ActionListener {

    private JFrame gui;
    private JTable table;

    public MainWindow() {
        gui = new JFrame("Phone Book");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(360, 360);
        gui.setLocationRelativeTo(null);

        String[] columns = {"Name", "Phone No."};
        String[][] data = {
                {"Dang Dinh Quan", "0982496005"},
                {"Vu Minh Tuan", "0982609010"}
        };
//        JTable table = new JTable(data, columns);
        DefaultTableModel tm = new DefaultTableModel(data, columns);
        table = new JTable();
        table.setModel(tm);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottom = new JPanel();
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(this);
        bottom.add(addButton);

        gui.add(scrollPane, BorderLayout.CENTER);
        gui.add(bottom, BorderLayout.SOUTH); // or add only button if ya want it to stretch
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow w = new MainWindow();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // the default table does not have add() method
        // thus, we have to use DefaultTableModel when creating JTable
        // table.getModel();
        // DefaultTableModel tm = (DefaultTableModel) table.getModel(); // TableModel
        // tm.addRow(new String[] {"Some name", "Some phone nio."});
        AddContactWindow addWindow = new AddContactWindow(table);
    }

    public void addRowByModel(DefaultTableModel tm) {
        table.setModel(tm);
    }
}
