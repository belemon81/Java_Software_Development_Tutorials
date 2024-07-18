package lects.lect08.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * SimpleDialogDemo requires these files:
 *   images/middle.gif
 */
public class SimpleDialogDemo implements ActionListener {
    private ImageIcon icon;

    private JFrame frame;

    /**
     * Creates the GUI shown inside the frame's content pane.
     */
    public SimpleDialogDemo() {
        icon = createImageIcon("../images/middle.gif");
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SimpleDialogDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new SimpleDialogDemo()).createAndShowGUI();
            }
        });
    }

    /**
     * create the default GUI to show
     */
    private void createAndShowGUI() {
        frame = new JFrame("Dialogs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components.
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton mDialog = new JButton("Message dialog");
        mDialog.addActionListener(this);
        mDialog.setPreferredSize(new Dimension(250, 20));
        pButtons.add(mDialog);
        mDialog = new JButton("Error message dialog");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);
        mDialog = new JButton("Warning message dialog");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);
        mDialog = new JButton("Plain message dialog");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);
        mDialog = new JButton("Option dialog");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);
        mDialog = new JButton("Input dialog");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);
        mDialog = new JButton("Input dialog (with combo)");
        mDialog.setPreferredSize(new Dimension(250, 20));
        mDialog.addActionListener(this);
        pButtons.add(mDialog);

        pButtons.add(mDialog);

        frame.add(pButtons);

        // status panel
        JLabel status = new JLabel("Ok.");
        status.setOpaque(true);
        status.setBackground(Color.LIGHT_GRAY);
        frame.add(status, BorderLayout.SOUTH);

        frame.setSize(270, 270);
        // frame.pack();
        frame.setLocation(100, 100);
        frame.setVisible(true);
    }

    /**
     * implements ActionListener
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Message dialog")) {
            JOptionPane.showMessageDialog(frame, "Eggs aren't supposed to be green.");
        } else if (cmd.equals("Error message dialog")) {
            JOptionPane.showMessageDialog(frame, "Eggs aren't supposed to be green.",
                    "Inane error", JOptionPane.ERROR_MESSAGE);
        } else if (cmd.equals("Warning message dialog")) {
            JOptionPane.showMessageDialog(frame, "Eggs aren't supposed to be green.",
                    "Inane warn", JOptionPane.WARNING_MESSAGE);
        } else if (cmd.equals("Plain message dialog")) {
            JOptionPane.showMessageDialog(frame, "Eggs aren't supposed to be green.",
                    "Inane warn", JOptionPane.PLAIN_MESSAGE);
        } else if (cmd.equals("Option dialog")) {
            Object[] options = {"Có", "Không bao giờ!"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Bạn có muốn trứng thịt màu xanh không?", "Câu hỏi linh tinh",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (n == JOptionPane.YES_OPTION) {
                setLabel("You're kidding!");
            } else if (n == JOptionPane.NO_OPTION) {
                setLabel("I don't like them, either.");
            } else {
                setLabel("Come on -- 'fess up!");
            }
        } else if (cmd.equals("Input dialog")) {
            String s = (String) JOptionPane.showInputDialog(frame,
                    "Complete the sentence:\n" + "\"Green eggs and...\"",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE, icon, null, "ham");

            // If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                setLabel("Green eggs and... " + s + "!");
                return;
            }

        } else if (cmd.equals("Input dialog (with combo)")) {
            Object[] possibilities = {"ham", "spam", "yam"};
            String s = (String) JOptionPane.showInputDialog(frame,
                    "Complete the sentence:\n" + "\"Green eggs and...\"",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE, icon, possibilities,
                    "spam");

            // If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                setLabel("Green eggs and... " + s + "!");
                return;
            }
        }
    }

    /**
     * set the status text of the frame to mesg
     */
    private void setLabel(String mesg) {
        JLabel status = (JLabel) frame.getContentPane().getComponent(1);
        status.setText(mesg);
    }

}
