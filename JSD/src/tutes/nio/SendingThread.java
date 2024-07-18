package tutes.nio;

import java.io.OutputStream;
import java.io.PrintWriter;

public class SendingThread extends Thread {
    private PrintWriter out;

    private Application application;

    public SendingThread(OutputStream outputStream, Application application) {
        super();
        this.out = new PrintWriter(outputStream, true);
        this.application = application;
    }

    @Override
    public void run() {
        out.println(application.field.getText());
        application.field.setText("");
    }
}
