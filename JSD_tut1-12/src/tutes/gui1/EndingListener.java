package tutes.gui1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a simple action event listener that ends the program
public class EndingListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // code #1
        // end program
        FirstWindow.endProgram();
    }
}
