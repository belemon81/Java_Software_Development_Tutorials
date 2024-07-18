package lects.lect11.knockknock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProtocol extends Protocol {

    protected Socket kkSocket = null;

    private BufferedReader stdIn;

    public ClientProtocol() {
        super();

        stdIn = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void exchange() {
        String inputLine;
        String outputLine;

        // start the exchange
        try {
            while (true) {
                // read next input
                inputLine = read();

                System.out.println("Server: " + inputLine);

                if (inputLine != null) {
                    // process input
                    outputLine = processInput(inputLine);

                    if (outputLine != null) {
                        // next response
                        outputLine = createNewMessage(outputLine);

                        System.out.println("Server: " + outputLine);
                        // send response
                        send(outputLine);
                    }
                }
            }
        } catch (EOPException e) {
            // end-of-grame
            // print Bye.
            System.out.println(e.getMessage());
        }
    }

    public void connect(final String host, final int port)
            throws UnknownHostException, IOException {
        kkSocket = new Socket(host, port);

        out = new PrintWriter(kkSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
    }

    // overides super-class method
    public String processInput(String fromServer) throws EOPException {
        String fromUser = null;

        if (fromServer.equals("Bye."))
            throw new EOPException("Bye.");

        try {
            fromUser = stdIn.readLine();

        } catch (IOException e) {
            System.err.println("KKCP.processInput: Failed to get user input");
        }

        return fromUser;
    }

    /**
     * @effects releases <code>this</code>.
     */
    public void close() {
        super.close();
        try {
            kkSocket.close();
            stdIn.close();
        } catch (Exception e) {
            // ignore
        }
    }
}
