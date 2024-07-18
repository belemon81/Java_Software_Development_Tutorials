package lects.lect09.drawing;

import javax.swing.*;
import java.awt.*;

public class HappyFaceColorPanel {
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 400;


    public static void main(String[] args) {
        (new HappyFaceColorPanel()).createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Happy face in a panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create two happy faces in a panel, which is added to
        // the center of the frame
        int x = 100, y = 100;
        final HappyFace face = new HappyFace(x, y);

        // use happy face as the content pane of the frame
        // the display area of the face is defined by the content pane
        frame.setContentPane(face);

        // show the frame
        //frame.getContentPane().setBackground(Color.white);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
    }

    /**
     * draws a happy face as a sub-class of JComponent
     **/
    private static class HappyFace extends JComponent {
        public static final int FACE_DIAMETER = 200;
        public static final int EYE_WIDTH = 20;
        public static final int EYE_HEIGHT = 10;
        public static final int MOUTH_WIDTH = 100;
        public static final int MOUTH_HEIGHT = 50;
        public static final int MOUTH_START_ANGLE = 180;
        public static final int MOUTH_ARC_SWEEP = 180;

        public int xFace;
        public int yFace;

        /**
         * initialises this with an initial (x,y) coordinate
         */
        public HappyFace(int x, int y) {
            this.xFace = x;
            this.yFace = y;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // keep the existing color before use
            Color oldColor = g.getColor();

            // fill the area with white
            // only need when this component is used as a content pane
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            int X_RIGHT_EYE = xFace + 55;
            int Y_RIGHT_EYE = yFace + 60;
            int X_LEFT_EYE = xFace + 130;
            int Y_LEFT_EYE = yFace + 60;

            int X_MOUTH = xFace + 50;
            int Y_MOUTH = yFace + 100;

            g.setColor(Color.BLACK);

            // draw the origin of this drawing space
            g.setColor(Color.RED);
            g.fillOval(0, 0, 5, 5);
            g.drawString("(0,0)", 0, 15);
            g.setColor(Color.BLACK);

            // draw x,y of the face
            g.drawString("(x,y): " + "(" + xFace + "," + yFace + ")",
                    xFace + (int) (FACE_DIAMETER * 0.5), yFace + FACE_DIAMETER + 20);

            // Default is equivalent to: g.setColor(Color.black);
            g.drawOval(xFace, yFace, FACE_DIAMETER, FACE_DIAMETER);
            // Draw Eyes:
            g.setColor(Color.BLUE);
            g.fillOval(X_RIGHT_EYE, Y_RIGHT_EYE, EYE_WIDTH, EYE_HEIGHT);
            g.fillOval(X_LEFT_EYE, Y_LEFT_EYE, EYE_WIDTH, EYE_HEIGHT);
            // Draw Mouth:
            g.setColor(Color.RED);
            g.drawArc(X_MOUTH, Y_MOUTH, MOUTH_WIDTH, MOUTH_HEIGHT, MOUTH_START_ANGLE,
                    MOUTH_ARC_SWEEP);

            // reset color
            g.setColor(oldColor);

        }
    }
}
