package lects.lect11.oneway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("localhost", 4444);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        sk.getInputStream()
                )
        );
        String fromServer;
        while ((fromServer = br.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("bye")) {
                break;
            }
        }
        br.close();
        sk.close();
    }
}
