package lects.lect07.listeners;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
        JComponent source = (JComponent) e.getSource();
        String name = null;
        if (source instanceof JTextComponent) {
            name = ((JTextComponent) source).getText();
        } else if (source instanceof JLabel) {
            name = ((JLabel) source).getText();
        } else {
            name = source.getName();
        }

        String type = source.getClass().getSimpleName();
        System.out.println("Mouse clicked on " + type + "(" + name + ")");
    }
}
