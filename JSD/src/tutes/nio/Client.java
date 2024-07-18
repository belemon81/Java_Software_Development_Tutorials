package tutes.nio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Client extends Application implements ActionListener {
    Socket socket;

    public Client() {
        super();
        sendBtn.addActionListener(this);
        gui.setVisible(true);
        try {
            socket = new Socket("localhost", 3333);
            Thread receiving = new ReceivingThread(socket.getInputStream(), this);
            receiving.start();
            receiving.join();
            socket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Send") && field.getText() != null && !field.getText().equals("")) {
            history.append("* You : " + field.getText() + "\n");
            // 1. Whenever a message arrives from client, show that message
            // 2. Whenever we type a message, send it to client
            //    The order of sending and receiving are independent
            Thread sending;
            if (socket != null) {
                try {
                    sending = new SendingThread(socket.getOutputStream(), this);
                    sending.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                field.setText("");
            }
        }
    }
}
