package lects.lect11.knockknocksingle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private ServerSocket socket = null;
    private Protocol prot;

    // the run method
    public static void main(String[] args) {

        // start the server socket
        final int port = 4444;

        Server server = new Server();

        try {
            server.listen(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        // wait for client connection
        try {
            server.waits();
        } catch (IOException e) {
            System.err.println("Fail to accept a client connection");
            System.exit(1);
        }

        // data exchange
        server.exchange();

        // close and end
        server.close();
    }

    // connect to port
    public void listen(int port) throws UnknownHostException, IOException {
        socket = new ServerSocket(port);
    }

    // wait for connection
    public void waits() throws IOException {
        Socket clientSocket = null;
        try {
            clientSocket = socket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));

        // prepare the protocol
        prot = new Protocol(in, out);
    }

    // exchange data with client
    public void exchange() {
        String inputLine, outputLine;
        try {
            // sends the initial message
            outputLine = prot.processClientInput(null);
            outputLine = prot.createNewMessage(outputLine);
            prot.send(outputLine);

            // proceed until end-of-game
            while (true) {
                // read next input
                inputLine = prot.read();

                // process input
                if (inputLine != null) {
                    outputLine = prot.processClientInput(inputLine);

                    if (outputLine != null) {
                        // new response
                        outputLine = prot.createNewMessage(outputLine);

                        // send response
                        prot.send(outputLine);
                    }
                }
            }
        } catch (EOGException e) {
            // end-of-game
            // send Bye.
            prot.send(e.getMessage());
        }
    }

    // close the server
    public void close() {
        try {
            socket.close();
            prot.close();
        } catch (Exception e) {
            // ignore
        }
    }
}
