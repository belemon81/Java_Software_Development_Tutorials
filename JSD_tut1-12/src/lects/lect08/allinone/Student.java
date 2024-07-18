package lects.lect08.allinone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @overview Represents a standard desktop GUI
 */
public class Student extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private JPanel north, south, centre;
    private JLabel title;
    private JButton ok, cancel;
    private JLabel lbl1, lbl2;
    private JTextField tf1, tf2;

    // model
    private String name;
    private String address;

    /**
     * @effects initialise <tt>gui</tt> and the display components
     */
    public Student() {
        createGUI();
    }

    public static void main(String[] args) {
        Student app = new Student();
        app.display();
    }

    /**
     * @effects initialise <tt>gui</tt> with a menu bar; initialise
     * the display components and add them to <tt>gui</tt>
     */
    private void createGUI() {
        // the window
        gui = new JFrame("My application");
        gui.addWindowListener(this);

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);
        menuBar.add(file);

        gui.setJMenuBar(menuBar);

        // the panels
        north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gui.add(north, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centre = new JPanel(new GridLayout(2, 2));
        // put centre inside another panel to prevent its size
        // from being readjusted when user resizes the window
        centrePanel.add(centre);
        //centre.setBorder(
        //    BorderFactory.createEtchedBorder());
        gui.add(centrePanel, BorderLayout.CENTER);
        south = new JPanel();
        gui.add(south, BorderLayout.SOUTH);

        // the title
        title = new JLabel("Enter details");
        north.add(title);

        // the buttons
        ok = new JButton("Ok");
        ok.setActionCommand("Okay");

        ok.addActionListener(this);

        cancel = new JButton("Reset");
        cancel.addActionListener(this);
        south.add(ok);
        south.add(cancel);

        // the labels
        lbl1 = new JLabel("Name:");
        lbl1.setDisplayedMnemonic('N');
        lbl2 = new JLabel("Address: ");
        lbl2.setDisplayedMnemonic('A');

        // the text fields
        tf1 = new JTextField(10);
        tf2 = new JTextField(20);

        // bind labels to text fields
        lbl1.setLabelFor(tf1);
        lbl2.setLabelFor(tf2);

        centre.add(lbl1);
        centre.add(tf1);
        centre.add(lbl2);
        centre.add(tf2);

        // set up window
        gui.pack();
    }

    /**
     * @effects show <tt>gui</tt>
     */
    public void display() {
        if (!gui.isVisible())
            gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Okay")) {
            processInput();
        } else if (cmd.equals("Accept")) {
            System.out.println("The command is 'Accept'");
        } else if (cmd.equals("Reset")) {
            clearInput();
        } else if (cmd.equals("Exit")) {
            shutDown();
        } // add other cases here
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shutDown();
    }

    /**
     * @effects read user input on <tt>tf1,tf2</tt> and process them
     */
    private void processInput() {
        String inputName = tf1.getText();
        String inputAddress = tf2.getText();

        if (inputName.equals("") || inputAddress.equals("")) {
            JOptionPane.showMessageDialog(gui, "Name and Address cannot be empty!",
                    "Missing required inputs", JOptionPane.ERROR_MESSAGE);
        } else {
            this.name = inputName;
            this.address = inputAddress;
            JOptionPane.showMessageDialog(gui, "Name and Address have been set successfully!");
        }
    }

    /**
     * @effects sets the texts of <tt>tf1, tf2</tt> to empty.
     */
    private void clearInput() {
        tf1.setText("");
        tf2.setText("");
    }

    /**
     * Shut down this application.
     *
     * @effects dispose <tt>gui</tt> and exit.
     */
    private void shutDown() {
        gui.dispose();
        System.exit(0);
    }
}
