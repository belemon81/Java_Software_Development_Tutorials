package lects.lect08;

import javax.swing.*;

public class ConfirmationDialogDemo {
    public static void main(String[] args) {
        int ans = JOptionPane.showOptionDialog(null,
                "message", "title",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"OK", "Cancel"},
                null);
        if (ans == JOptionPane.YES_OPTION) {
            System.out.println("Deleting...");
        } else {
            System.out.println("Doing nothing...");
        }
    }
}
