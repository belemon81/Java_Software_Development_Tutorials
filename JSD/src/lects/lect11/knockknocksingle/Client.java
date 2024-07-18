package lects.lect11.knockknocksingle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private Protocol prot;

    // the run method
    public static void main(String[] args) {

        final String host = "localhost";
        final int port = 4444;

        Client c = new Client();

        try {
            c.connect(host, port);
        } catch (IOException e) {
            System.err.printf("Could not connecto to %s:%d%n", host, port);
            System.exit(1);
        }

        c.exchange();

        c.close();
    }

    // connect to server
    public void connect(String host, int port) throws UnknownHostException,
            IOException {
        socket = new Socket(host, port);
        // connect to server
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // prepare the protocol
            prot = new Protocol(in, out);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }
    }

    // exchange data with server
    public void exchange() {
        String inputLine;
        String outputLine;

        // data exchange
        try {
            while (true) {
                // read next input
                inputLine = prot.read();

                System.out.println("Server: " + inputLine);

                if (inputLine != null) {
                    // process input
                    outputLine = prot.processServerInput(inputLine);

                    if (outputLine != null) {
                        // next response
                        outputLine = prot.createNewMessage(outputLine);

                        System.out.println("Server: " + outputLine);
                        // send response
                        prot.send(outputLine);
                    }
                }
            }
        } catch (EOGException e) {
            // end-of-grame
            // print Bye.
            System.out.println(e.getMessage());
        }
    }

    // close the client
    public void close() {
        prot.close();
    }
}
