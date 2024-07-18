package lects.lect11.knockknockmulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProtocol extends Protocol {

    protected Socket socket;

    private BufferedReader stdIn;

    public ClientProtocol() {
        super();

        stdIn = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Creates a network connection to <code>host:port</code>.
     */
    public void connect(final String host, final int port)
            throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    // overrides super-class method
    public String processInput(String fromServer) throws EOPException {
        String fromUser = null;

        if (fromServer.equals("Bye."))
            throw new EOPException("Bye.");

        try {
            fromUser = stdIn.readLine();

        } catch (IOException e) {
            System.err.println("ClientProtocol.processInput: Failed to get user input");
        }

        return fromUser;
    }

    /**
     * @effects releases <code>this</code>.
     */
    public void close() {
        super.close();
        try {
            socket.close();
            stdIn.close();
        } catch (Exception e) {
            // ignore
        }
    }
}
