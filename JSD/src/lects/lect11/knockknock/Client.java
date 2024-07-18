package lects.lect11.knockknock;

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

        // start a new client
        Client client = new Client();

        try {
            client.connect(host, port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // exchange data with server
        client.exchange();

        // disconnect & close
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
