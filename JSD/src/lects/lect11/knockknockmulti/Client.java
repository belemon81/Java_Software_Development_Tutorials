package lects.lect11.knockknockmulti;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
    private ClientProtocol prot;

    public Client() {
        prot = new ClientProtocol();
    }

    // run method
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 4444;

        // creates a client
        Client client = new Client();

        // connect
        try {
            client.connect(host, port);
        } catch (IOException e) {
            System.err.printf("Could not connecto to %s:%d%n", host, port);
            System.exit(1);
        }

        // exchange data with server
        client.exchange();

        // close
        client.close();
    }

    // connect to server
    public void connect(String host, int port) throws UnknownHostException,
            IOException {
        prot.connect(host, port);
    }

    // exchange data with server
    public void exchange() {
        prot.exchange();
    }

    // close the client
    public void close() {
        prot.close();
    }
}
