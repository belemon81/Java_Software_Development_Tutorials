package tutes.db.phonebook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

public class PhoneBook extends WindowAdapter implements ActionListener {
    ArrayList<Object[]> mapRows;
    private JFrame gui;
    private AddContact addGUI;
    private JTable tblContacts;
    // TODO: make some reusable attributes
    private Connection conn = null;
    private Statement stmt = null;
    private JScrollPane scrContacts;

    public PhoneBook() {
        // TODO: connect to DB (2)
        connectDB();
        createGUI();
    }

    public static void main(String[] args) {
        PhoneBook app = new PhoneBook();
        app.display();
    }

    // TODO: connect to DB (1)
    private void connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db/phonebook.sqlite3");
            stmt = conn.createStatement();
            System.out.println("Connect to DB!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to DB!");
            shutDown();
        }
    }

    // TODO: execute a SELECT query to create table of data (1)
    private void loadContactsTable() {
        String sql = "SELECT * FROM contacts ORDER BY id ASC";
        Object[][] data = null;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            mapRows = new ArrayList<>();
            while (rs.next()) {
                mapRows.add(new Object[]{
                        rs.getInt("ID"), rs.getString("Name"), rs.getString("Phone"), false
                });
            }
            data = new Object[mapRows.size()][3];
//            data = mapRows.toArray(data);
            for (int i = 0; i < data.length; i++) {
                data[i] = new Object[]{mapRows.get(i)[1], mapRows.get(i)[2], mapRows.get(i)[3]};
            }

            System.out.println("Successfully retrieve phonebook data!");
        } catch (SQLException e) {
            System.out.println("Failed to fetch data from DB!");
            data = new Object[][]{
                    {"Failed to fetch", "phonebook data", false}
            };
        }

        String[] headers = {"Name", "Phone", ""};
        DefaultTableModel tm = new DefaultTableModel(data, headers) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        tblContacts = new JTable(tm);
        tblContacts.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblContacts.getColumnModel().getColumn(1).setPreferredWidth(120);
    }

    private void createGUI() {
        gui = new JFrame("Phone Book");
        gui.setSize(360, 300);
        gui.setLocationRelativeTo(null);
        gui.addWindowListener(this);
//        gui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);
        menu.add(fileMenu);
        gui.setJMenuBar(menu);

        // north panel
        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(Color.YELLOW);
        JLabel lblTitle = new JLabel("Phone Book Application");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 15f));
        pnlTop.add(lblTitle);
        gui.add(pnlTop, BorderLayout.NORTH);

        // center panel
        JPanel pnlMiddle = new JPanel();
        pnlMiddle.setLayout(new BorderLayout());
        gui.add(pnlMiddle);
        // TODO: execute SELECT statement to get table data (2)
        loadContactsTable();
        scrContacts = new JScrollPane(tblContacts);
        pnlMiddle.add(scrContacts);

        // bottom
        JPanel pnlBottom = new JPanel();
        gui.add(pnlBottom, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        pnlBottom.add(btnAdd);

        JButton btnCheckAll = new JButton("Check All");
        btnCheckAll.addActionListener(this);
        pnlBottom.add(btnCheckAll);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        pnlBottom.add(btnDelete);
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
        } else if (command.equals("Check All")) {
            for (int i = 0; i < tblContacts.getRowCount(); i++) {
                tblContacts.setValueAt(true, i, 2);
            }
        } else if (command.equals("Delete")) {
            int result = JOptionPane.showConfirmDialog(gui, "Are you sure?", "Delete confirmation", JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                for (int i = tblContacts.getRowCount() - 1; i >= 0; i--) {
                    DefaultTableModel tm = (DefaultTableModel) tblContacts.getModel();
                    boolean delete = (boolean) tm.getValueAt(i, 2);
                    if (delete) {
                        try {
                            stmt.execute("DELETE FROM contacts WHERE ID =" + (mapRows.get(i)[0]));
                        } catch (SQLException ex) {
                            System.out.println("Failed to insert to DB!");
                        }
                    }
                }
                loadContactsTable();
                scrContacts.setViewportView(tblContacts);
                gui.validate();
            }
        } else if (command.equals("Exit")) {
            shutDown();
        }
    }

    private void shutDown() {
        // TODO: close the DB (1)
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                System.out.println("Failed to close the DB connection!");
            }
        }

        System.exit(0);
    }

    public void addContact(String name, String phone) {
//        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();
//        model.addRow(new Object[]{name, phone, false});
        try {
            stmt.execute("INSERT INTO contacts(name,phone) VALUES (" + name + "," + phone + ")");
        } catch (SQLException e) {
            System.out.println("Failed to insert to DB!");
        }
        // reload JTable
        loadContactsTable();
        scrContacts.setViewportView(tblContacts);
        gui.validate();
    }
}