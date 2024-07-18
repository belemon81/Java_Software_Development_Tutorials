package lects.lect06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is the View and Controller part in an MVC application.
 * The Model is implemented in MyClass.java.
 * Whenever the Model's data is modified, the View is automatically updated.
 */
public class DisplayWindow extends JFrame implements PropertyChangeListener {

    private JLabel lbl;
    private MyClass model;

    public DisplayWindow(String title) throws HeadlessException {
        super(title);
        lbl = new JLabel();
        add(lbl, BorderLayout.NORTH);
        JButton b = new JButton("Set value");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setLabel("New label");
            }
        });
        add(b, BorderLayout.SOUTH);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        model = new MyClass();
        model.addPropertyChangeListener(this);
        model.setLabel("Old label");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("label")) {
            lbl.setText((String) evt.getNewValue());
        }
    }

}
