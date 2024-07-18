package lects.lect11.knockknockmulti;

import java.io.IOException;

public class Server {
    private ServerProtocol prot;

    public Server() {
        prot = new ServerProtocol();
    }

    // run method
    public static void main(String[] args) {

        final int port = 4444;

        // creates a server
        Server server = new Server();

        // start the socket
        try {
            server.listen(port);
            System.out.println("Server started on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }


        // waits for client connections
        try {
            server.waits();
        } catch (IOException e) {
            System.err.println("Failed to accept a client connection");
            System.exit(1);
        }

        // exchange data with the client
        server.exchange();

        // close
        // server.close();
    }

    // connect to port
    public void listen(int port) throws IOException {
        prot.listen(port);
    }

    // wait for connection
    public void waits() throws IOException {
        prot.waits();
    }

    // exchange data with client
    public void exchange() {
        prot.exchange();
    }

    public void close() {
        prot.close();
    }
}
