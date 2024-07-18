package tutes.nio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application implements ActionListener {
    ServerSocket serverSocket;
    Socket clientSocket;

    public Server() {
        super();
        gui.setVisible(true);
        sendBtn.addActionListener(this);
        try {
            serverSocket = new ServerSocket(3333);
            clientSocket = serverSocket.accept();
            Thread receiving = new ReceivingThread(clientSocket.getInputStream(), this);
            receiving.start();
            receiving.join();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Send") && field.getText() != null && !field.getText().equals("")) {
            history.append(String.format("* You : " + field.getText() + "\n"));
            // 1. Whenever a message arrives from client, show that message
            // 2. Whenever we type a message, send it to client
            //    The order of sending and receiving are independent
            Thread sending;
            if (clientSocket != null) {
                try {
                    sending = new SendingThread(clientSocket.getOutputStream(), this);
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
