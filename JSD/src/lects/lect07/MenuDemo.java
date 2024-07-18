package lects.lect07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDemo {
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        final JFrame w = new SimpleWindow(BorderLayoutDemo.class.getName(), 300, 200);
        w.setLayout(new BorderLayout());

        // set menu bar
        createMenu(w);

        final Dimension wz = w.getSize();

        // create a title label to put in the north
        JLabel title = PanelDemo.createLabel("Enter details", wz.width - 20, 20);
        w.add(title, BorderLayout.NORTH);

        // create a detailed panel to put in the center
        JPanel panel = PanelDemo.createDetailedPanel(wz.width - 20, wz.height - 100);
        w.add(panel, BorderLayout.CENTER);

        // create a button panel to put in the south
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new JButton("OK"));
        w.add(buttonPanel, BorderLayout.SOUTH);

        // Display the window.
        w.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    // change the foreground colours of the components of w
    private static void changeColor(Container cont, Color c) {
        Component[] comps;
        if (cont instanceof JMenu) {  // menu components are retrieved different
            comps = ((JMenu) cont).getMenuComponents();
        } else {
            comps = cont.getComponents();
        }

        for (Component comp : comps) {
            if (comp instanceof JPanel) {
                changeColor((JPanel) comp, c);
            } else if (comp instanceof JMenu) {
                JMenu menu = (JMenu) comp;
                menu.setForeground(c);  // change menu color
                changeColor(menu, c);   // change colors of menu items
            } else if (comp instanceof JComponent) {
                ((JComponent) comp).setForeground(c);
            }
        }
    }

    private static void createMenu(final JFrame w) {
        // the menu bar
        final JMenuBar bar = new JMenuBar();

        // action listener to handle user click on menu
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String menuItem = e.getActionCommand();
                if (menuItem.equals("Red")) {
                    changeColor(w.getContentPane(), Color.RED);
                    changeColor(bar, Color.RED);
                } else if (menuItem.equals("White")) {
                    changeColor(w.getContentPane(), Color.WHITE);
                    changeColor(bar, Color.WHITE);
                } else if (menuItem.equals("Blue")) {
                    changeColor(w.getContentPane(), Color.BLUE);
                    changeColor(bar, Color.BLUE);
                } else if (menuItem.equals("Default")) {
                    changeColor(w.getContentPane(), Color.BLACK);
                    changeColor(bar, Color.BLACK);
                } else {
                    System.out.println("Unknown action " + menuItem);
                }
            }
        };

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                w.dispose();
            }
        });
        fileMenu.add(exit);

        // nested menu
        JMenu saveMenu = new JMenu("Save");
        JMenuItem toFile = new
                JMenuItem("to file");
        JMenuItem toDB = new
                JMenuItem("to database");
        saveMenu.add(toFile);
        saveMenu.add(toDB);
        fileMenu.add(saveMenu);

        // Tools menu
        JMenu colorMenu = new JMenu("Tools");

        JMenuItem redChoice = new JMenuItem("Red");
        redChoice.addActionListener(listener);
        colorMenu.add(redChoice);

        JMenuItem whiteChoice = new JMenuItem("White");
        whiteChoice.addActionListener(listener);
        colorMenu.add(whiteChoice);

        JMenuItem blueChoice = new JMenuItem("Blue");
        blueChoice.addActionListener(listener);
        colorMenu.add(blueChoice);

        colorMenu.addSeparator();
        JMenuItem defChoice = new JMenuItem("Default");
        defChoice.addActionListener(listener);
        colorMenu.add(defChoice);

        bar.add(fileMenu);
        bar.add(colorMenu);
        w.setJMenuBar(bar);
    }
}
