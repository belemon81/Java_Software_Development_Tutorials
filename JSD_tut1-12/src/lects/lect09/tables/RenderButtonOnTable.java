package lects.lect09.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RenderButtonOnTable {
    public static void main(String[] args) {
        String[] head = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith", "Snowboarding", 5, false},
                {"John", "Doe", "Rowing", 3, true},
                {"Sue", "Black", "Knitting", 2, false},
                {"Jane", "White", "Speed reading", 20, true},
                {"Joe", "Brown", "Pool", 10, false}};

        MyTableModel mtm = new MyTableModel(data, head);
        JTable table = new JTable(mtm);
        table.setDefaultRenderer(String.class, new MyCellRenderer());
        JScrollPane spane = new JScrollPane(table);

        JFrame frame = new JFrame("ButtonTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(spane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static class MyCellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JButton(value.toString());
        }
    }

    static class MyTableModel extends DefaultTableModel {
        public MyTableModel(Object[][] data, String[] head) {
            super(data, head);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 1) {
                return String.class;
            } else {
                return super.getColumnClass(columnIndex);
            }
        }
    }
}
