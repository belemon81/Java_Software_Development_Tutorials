package tutes.gui4.phonebook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PhoneBook extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private AddContact addGUI;
    private JTable tblContacts;

    public PhoneBook() {
        createGUI();
    }

    public static void main(String[] args) {
        PhoneBook app = new PhoneBook();
        app.display();
    }

    private void createGUI() {
        gui = new JFrame("Phone Book");
        gui.setSize(300, 300);
        gui.addWindowListener(this); /// window closing

        // TODO: 1. Create menu
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        mb.add(fileMenu);
        gui.setJMenuBar(mb);

        // TODO: 2. Make Exit menu item to call the shutDown() method
        // check actionPerformed()
        exit.addActionListener(this);

        // TODO: 3. Add title with yellow background
        JLabel title = new JLabel("Phone Book Application");
        title.setOpaque(true); // transparent if false
        title.setBackground(Color.YELLOW);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(16f));
        title.setPreferredSize(new Dimension(30, 30));
        gui.add(title, BorderLayout.NORTH);

        // center panel
        JPanel pnlMiddle = new JPanel();
        pnlMiddle.setLayout(new BorderLayout());
        gui.add(pnlMiddle);

        // TODO: 4. Add the third column for checkbox
        String[] headers = {"Name", "Phone", ""};
        Object[][] data = {
                {"Vu Minh Tuan", "0982609010", false},
                {"Dang Dinh Quan", "0982496005", false},
        };

        // TODO: 4. Override the type of the third column into boolean
        DefaultTableModel tm = new DefaultTableModel(data, headers) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        };

        tblContacts = new JTable(tm);
        JScrollPane scrContacts = new JScrollPane(tblContacts);
        pnlMiddle.add(scrContacts);

        // bottom
        JPanel pnlBottom = new JPanel();
        gui.add(pnlBottom, BorderLayout.SOUTH);

        // TODO: 5. Rename into "Add" (also modify the actionPerformed() method)
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        pnlBottom.add(btnAdd);

        // TODO: 6. Add "Check All"
        JButton btnCheckAll = new JButton("Check All");
        btnCheckAll.addActionListener(this);
        pnlBottom.add(btnCheckAll);


        // TODO: 6. Add "Delete"
        JButton btnDel = new JButton("Delete");
        btnDel.addActionListener(this);
        pnlBottom.add(btnDel);

        gui.setLocationRelativeTo(null);
    }

    public void display() {
        gui.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shutDown();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add")) {
            if (addGUI == null) addGUI = new AddContact(gui, this);
            addGUI.display();
        } else if (command.equals("Exit")) {
            shutDown();
        }
        // TODO: 6. Add "Delete" action
        else if (command.equals("Delete")) {
            // TODO: 7. Confirmation Dialog
            int nRows = tblContacts.getRowCount();
            int index = nRows - 1;
            boolean checked = false;

            for (int i = index; i >= 0; i--) {
                if ((boolean) tblContacts.getValueAt(i, 2)) {
                    checked = true;
                    index = i;
                    break;
                }
            }

            if (checked) {
                String[] options = {"Ok", "Cancel"};
                int opt = JOptionPane.showOptionDialog(gui,
                        "Are you sure?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options, options[0]);
                if (opt == JOptionPane.YES_OPTION) {
                    DefaultTableModel tm = (DefaultTableModel) tblContacts.getModel();
                    for (int i = index; i >= 0; i--) {
                        if ((boolean) tblContacts.getValueAt(i, 2)) {
                            tm.removeRow(i);
                        }
                    }
                }
            }
        }
        // TODO: 6. Add "Check All" action
        else if (command.equals("Check All")) {
            int nRows = tblContacts.getRowCount();
            for (int i = 0; i < nRows; i++) {
                tblContacts.setValueAt(true, i, 2);
            }
        }
    }

    private void shutDown() {
        System.exit(0);
    }

    public void addContact(String name, String phone) {
        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();
        model.addRow(new String[]{name, phone});
    }
}
