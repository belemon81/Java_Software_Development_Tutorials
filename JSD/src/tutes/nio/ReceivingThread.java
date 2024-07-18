package tutes.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReceivingThread extends Thread {
    private BufferedReader in;
    private Application application;
    private String other;

    public ReceivingThread(InputStream inputStream, Application application) {
        super();
        this.in = new BufferedReader(new InputStreamReader(inputStream));
        this.application = application;
        if (application.getClass().getSimpleName().equals("Server")) {
            other = "Client";
        } else {
            other = "Server";
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readLine();
                if (msg == null) {
                    break;
                } else if (msg.equals("bye")) {
                    application.history.append("* " + other + " : " + msg + "\n");
                    application.history.append("--------- Chatroom has closed ---------\n");
                    break;
                } else {
                    application.history.append("* " + other + " : " + msg + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
