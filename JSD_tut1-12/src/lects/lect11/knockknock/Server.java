package lects.lect11.knockknock;

import java.io.IOException;

/**
 * The server of the knock-knock game. Supports multiple client connections.
 */
public class Server {
    private ServerProtocol prot;

    public Server() {
        prot = new ServerProtocol();
    }

    // run method
    public static void main(String[] args) {

        final int port = 4444;

        // create a server
        Server server = new Server();

        // listens on port
        try {
            server.listen(port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // wait for clients connection and exchange data with them using threads
        server.exchange();
    }

    // connect to port
    public void listen(int port) throws IOException {
        prot.listen(port);
    }

    // exchange data with clients
    public void exchange() {
        // use threads
        prot.exchangeThreaded();
    }

    // close
    public void close() {
        prot.close();
    }
} // end Server
