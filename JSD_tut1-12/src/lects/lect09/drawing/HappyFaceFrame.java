package lects.lect09.drawing;

import javax.swing.*;
import java.awt.*;

public class HappyFaceFrame extends JFrame {
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 400;

    public static final int FACE_DIAMETER = 200;
    public static final int X_FACE = 100;
    public static final int Y_FACE = 100;

    public static final int EYE_WIDTH = 20;
    public static final int EYE_HEIGHT = 10;
    public static final int X_RIGHT_EYE = X_FACE + 55;
    public static final int Y_RIGHT_EYE = Y_FACE + 60;
    public static final int X_LEFT_EYE = X_FACE + 130;
    public static final int Y_LEFT_EYE = Y_FACE + 60;

    public static final int MOUTH_WIDTH = 100;
    public static final int MOUTH_HEIGHT = 50;
    public static final int X_MOUTH = X_FACE + 50;
    public static final int Y_MOUTH = Y_FACE + 100;
    public static final int MOUTH_START_ANGLE = 30;
    public static final int MOUTH_ARC_SWEEP = 300;

    public HappyFaceFrame() {
        super("Happy face on a frame");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
    }

    public static void main(String[] args) {
        HappyFaceFrame drawing = new HappyFaceFrame();
        drawing.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);

        g.drawOval(X_FACE, Y_FACE, FACE_DIAMETER, FACE_DIAMETER);
        // Draw Eyes:
        g.fillOval(X_RIGHT_EYE, Y_RIGHT_EYE, EYE_WIDTH, EYE_HEIGHT);
        g.fillOval(X_LEFT_EYE, Y_LEFT_EYE, EYE_WIDTH, EYE_HEIGHT);
        // Draw Mouth:
        g.drawArc(X_MOUTH, Y_MOUTH, MOUTH_WIDTH, MOUTH_HEIGHT, MOUTH_START_ANGLE,
                MOUTH_ARC_SWEEP);
    }
}
