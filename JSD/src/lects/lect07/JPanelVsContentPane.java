package lects.lect07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JPanelVsContentPane {
    public static void main(String[] args) {
        JFrame w = new JFrame();
        w.setSize(450, 300);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        w.add(new JLabel("this is some text"), BorderLayout.WEST);
        JTextField tf = new JTextField(30);
        w.add(tf, BorderLayout.EAST);
        JComboBox jcb = new JComboBox(new String[]{"a", "b", "c"});
        w.add(jcb, BorderLayout.NORTH);
        jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(e.getItem() + " : " + e.getStateChange());
            }
        });
        JButton b = new JButton("Get combo box value");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = jcb.getSelectedIndex();
                tf.setText("Selected index is: " + selectedIndex +
                        " and value is " + jcb.getSelectedItem());
            }
        });
        w.add(b, BorderLayout.SOUTH);
        w.validate();
    }
}
