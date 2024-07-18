package lects.lect07;

import javax.swing.*;
import java.awt.*;

public class LabelledTextFieldDemo {
    public static void main(String[] args) {
        String[] labels = {"name:", "address:"};
        char[] mnemonics = {'n', 'a'};

        // the window
        SimpleWindow w = new SimpleWindow("Labelled text", 300, 200);

        // labels and texts
        JLabel label;
        JTextField tf;
        final int labelWidth = 100;
        final int labelHeight = 20;
        int index = 0;
        for (String lbl : labels) {
            // the text field
            tf = new JTextField(15);
            //tf.addKeyListener(new SimpleKeyListener());

            // label
            label = new JLabel(lbl);
            label.setLabelFor(tf);
            label.setPreferredSize(new Dimension(labelWidth, labelHeight));
            label.setDisplayedMnemonic(mnemonics[index]);

            // add to window
            w.add(label);
            w.add(tf);
            index++;
        }

        // show window
        w.setVisible(true);
    }
}

