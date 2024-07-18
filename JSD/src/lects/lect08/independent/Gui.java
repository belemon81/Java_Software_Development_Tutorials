package lects.lect08.independent;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private Controller ctrl;
    private JFrame gui;
    private JPanel north, south, centre;
    private JLabel title;
    private JButton ok, cancel;
    private JLabel lbl1, lbl2;
    private JTextField tf1, tf2;

    /**
     * @effects initialise <tt>gui</tt> and the display components
     */
    public Gui(Controller ctrl) {
        this.ctrl = ctrl;
        createGUI();
    }

    public String getName() {
        return tf1.getText();
    }

    public String getAddress() {
        return tf2.getText();
    }

    /**
     * @effects initialise <tt>gui</tt> with a menu bar; initialise
     * the display components and add them to <tt>gui</tt>
     */
    private void createGUI() {
        // the window
        gui = new JFrame("My application");
        gui.addWindowListener(ctrl);

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(ctrl);
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
        ok.addActionListener(ctrl);
        cancel = new JButton("Cancel");
        cancel.addActionListener(ctrl);
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
     * @efffects show <tt>gui</tt>
     */
    public void display() {
        if (!gui.isVisible())
            gui.setVisible(true);
    }

    /**
     * @effects sets the texts of <tt>tf1, tf2</tt> to empty.
     */
    public void clear() {
        tf1.setText("");
        tf2.setText("");
    }

    /**
     * @effects dispose <tt>gui</tt>.
     */
    public void shutDown() {
        gui.dispose();
    }
}
