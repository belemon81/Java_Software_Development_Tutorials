package lects.lect07.listeners;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
        Component source = (Component) e.getSource();
        String name = null;
        if (source instanceof JTextComponent) {
            name = ((JTextComponent) source).getText();
        } else if (source instanceof JLabel) {
            name = ((JLabel) source).getText();
        } else {
            name = source.getName();
        }

        String type = source.getClass().getSimpleName();
        char key = e.getKeyChar();

        System.out.println("Key pressed on " + type + "(" + name + ")" + ": " + key);
    }
}