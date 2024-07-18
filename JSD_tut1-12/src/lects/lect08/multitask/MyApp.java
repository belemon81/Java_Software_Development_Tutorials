package lects.lect08.multitask;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * @overview Represents a standard desktop GUI that performs multi-tasks
 */
public class MyApp extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private JPanel north, south, centre;
    private JLabel title;
    private JButton ok, cancel;
    private JLabel lbl1, lbl2, lblOutput;
    private JTextField tf1, tf2;

    private TimerTask task;

    /**
     * @effects initialise <tt>gui</tt> and the display components
     */
    public MyApp() {
        task = new TimerTask();

        createGUI();

        // start task on a separate thread
        Thread t = new Thread(task);
        t.start();
    }

    public static void main(String[] args) {
        MyApp app = new MyApp();
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
        gui.setJMenuBar(menuBar);
        //menuBar.setOpaque(true);
        //menuBar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);
        menuBar.add(file);

        // add a JLabel to menu bar for displaying task progress
        createStatusPanel(menuBar, task);

        // the panels
        north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gui.add(north, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centre = new JPanel(new GridLayout(2, 2));
        // put centre inside another panel to prevent its size
        // from being readjusted when user resizes the window
        centrePanel.add(centre);
        gui.add(centrePanel, BorderLayout.CENTER);
        south = new JPanel();
        gui.add(south, BorderLayout.SOUTH);

        // the title
        title = new JLabel("Enter details");
        north.add(title);

        // the buttons
        ok = new JButton("Ok");
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        south.add(ok);
        south.add(cancel);

        // the labels
        lbl1 = new JLabel("Name:");
        lbl1.setDisplayedMnemonic('N');
        lbl2 = new JLabel("Address: ");
        lbl2.setDisplayedMnemonic('A');

        // the text fields
        tf1 = new JTextField(19);
        tf2 = new JTextField(19);

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
     * @effects initialise <tt>lblOutput</tt> whose mouse handler is <tt>mouseHandler</tt>;
     * add <tt>lblOutput</tt> at the end of <tt>parent</tt>
     */
    private void createStatusPanel(Container parent,
                                   MouseListener mouseHandler) {
        lblOutput = new JLabel("-");
        lblOutput.setOpaque(true);
        lblOutput.setBackground(Color.ORANGE);
        lblOutput.setForeground(Color.BLUE);
        lblOutput.setFont(new Font("Times", Font.PLAIN, 18));
        lblOutput.setPreferredSize(new Dimension(90, 20));
        lblOutput.setHorizontalAlignment(JLabel.CENTER);

        // handle mouse click to stop/start the task
        // and to highlight the label
        lblOutput.setFocusable(true);
        lblOutput.addMouseListener(mouseHandler);

        // add the label to a panel whose layout alignment is right
        JPanel lblPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblPanel.setOpaque(false); // transparent
        lblPanel.setSize(new Dimension(300, 20));
        lblPanel.add(lblOutput);

        // add the label panel to parent
        parent.add(lblPanel);
    }

    /**
     * @efffects show <tt>gui</tt>
     */
    public void display() {
        if (!gui.isVisible())
            gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Ok")) {
            processInput();
        } else if (cmd.equals("Cancel")) {
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
        String t1 = tf1.getText();
        String t2 = tf2.getText();

        // process input here
        System.out.printf("Name: %s%nAddress: %s%n", t1, t2);
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

    /**
     * @overview Represent a timer task that  gets the current time in h:m:s format
     * and display it on the window.
     */
    private class TimerTask extends MouseAdapter implements Runnable {
        private final Border borderRed = BorderFactory.createLineBorder(Color.RED, 2);
        private final Border borderGreen = BorderFactory.createLineBorder(Color.GREEN, 2);
        private boolean stopTask;
        private Calendar cal;

        public TimerTask() {
            stopTask = false;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // stop/start the task
            if (!stopTask) {
                stopTask = true;
            } else {
                stopTask = false;
            }
        }

        /**
         * @effects turn on label border and display tool tip text
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            lblOutput.setBorder((stopTask ? borderGreen : borderRed));
            lblOutput.setToolTipText("Click to " +
                    (stopTask ? "start" : "stop"));
        }

        /**
         * @effects turn of label border
         */
        @Override
        public void mouseExited(MouseEvent e) {
            lblOutput.setBorder(null);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                if (!stopTask) {
                    cal = Calendar.getInstance();
                    int h = cal.get(Calendar.HOUR_OF_DAY);
                    int m = cal.get(Calendar.MINUTE);
                    int s = cal.get(Calendar.SECOND);
                    lblOutput.setText(String.format("%2d:%2d:%2d", h, m, s));
                }
            }
        }
    } // end TimerTask
}
