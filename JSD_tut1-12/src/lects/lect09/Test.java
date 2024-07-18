package lects.lect09;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        JFrame gui = new JFrame("Font Test");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lbl = new JLabel("this is some text");
        Font f = lbl.getFont();
        f = f.deriveFont(20f);
        lbl.setFont(f);
        System.out.println(f.getName());
    }
}
