package tutes.gui1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FirstWindow extends JFrame {
    // default width and length of the window
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public FirstWindow() {
        super();
        setSize(WIDTH, HEIGHT);
        setTitle("First Window Class");

        // handle window closing event

        // use helper method
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a button
        JButton endButton = new JButton("Click to end program.");

        // TODO: handle action event of the default closing button
        MyWindowListener listener = new MyWindowListener();
        addWindowListener(listener);

        // handle action event of the button
        // endButton.addActionListener(new EndingListener());
        endButton.addActionListener(new MyClosingAction());

        // add button to default position on window (centre)
        add(endButton);

        // add button to the top of the window
        add(endButton, BorderLayout.NORTH);
    }

    public static void endProgram() {
        System.out.println("Window is closing...");
        System.exit(0);

        // not freeze the button

        // new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            throw new RuntimeException(e);
        //        }
        //        System.exit(0);
        //    }
        //}).start();

        // freeze the button

        // try {
        //     Thread.sleep(2000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        // System.exit(0);
    }

    public static void main(String[] args) {
        // create a window
        FirstWindow w = new FirstWindow();

        // display it
        w.setVisible(true);
    }

    // TODO: create an object that is both WindowListener and ActionListener
    private static class MyClosingAction extends WindowAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            endProgram();
        }
    }

    // TODO: share the same code with EndingListener
    private static class MyWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            // code #2
            super.windowClosing(e);
            endProgram();
            // ?   Why do the program not wait 2s then close when using thread?
            // ->  Because while the thread is running (2s), windowClosed is executed.
            //     Thus, the window closed before the thread finishes
        }
    }
}
