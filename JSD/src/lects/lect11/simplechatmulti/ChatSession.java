package lects.lect11.simplechatmulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatSession extends Thread {
    private Socket client;
    private String clientName;
    private PrintWriter out;
    private BufferedReader in;

    public ChatSession(Socket clientSocket) {
        client = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientName = in.readLine();
        } catch (IOException e) {
            System.out.println("Failed to create the chat session!");
        }
    }

    @Override
    public void run() {
        try {
            String clientMsg = "";
            while (!clientMsg.equals("bye")) {
                clientMsg = in.readLine();
                System.out.println(clientName + ": " + clientMsg);
            }
            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
