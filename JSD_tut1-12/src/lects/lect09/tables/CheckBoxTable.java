package lects.lect09.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxTable {
    public CheckBoxTable() {
        JFrame gui = new JFrame("Checkbox Table Demo");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] headers = {"Name", "Phone", ""};
        Object[][] data = {
                {"Quan", "0982496055", false},
                {"Tuan", "0982609010", false}
        };
        DefaultTableModel model = new DefaultTableModel(data, headers);

        JTable table = new JTable(model) {
            public boolean isCellEditable(int row, int col) {
                // return col == 2;
                // return false;
                return true;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0 || column == 1) {
                    return String.class;
                } else {
                    return Boolean.class;
                }
            }
        };
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        // table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.setRowHeight(42);
        table.setFont(table.getFont().deriveFont(18.0f));
        Font newHeaderFont = table.getTableHeader().getFont();
        newHeaderFont = newHeaderFont.deriveFont(18.0f);
        newHeaderFont = newHeaderFont.deriveFont(Font.BOLD);
        table.getTableHeader().setFont(newHeaderFont);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(570, 200));
        gui.add(sp);

        JButton btn = new JButton("Check all");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    table.setValueAt("abc" + i, i, 0);
                    table.setValueAt(true, i, 2);
                }
            }
        });
        gui.add(btn, BorderLayout.SOUTH);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        new CheckBoxTable();
    }
}