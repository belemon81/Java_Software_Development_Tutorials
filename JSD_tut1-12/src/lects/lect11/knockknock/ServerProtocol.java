/**
 * The server protocol of the knock-knock game. Supports multiple client connections.
 */

package lects.lect11.knockknock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProtocol extends Protocol implements Runnable {

    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;
    private ServerSocket socket;
    private int state;
    private int currentJoke = 0;

    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here!",
            "I didn't know you could yodel!", "Bless you!",
            "Is there an owl in here?", "Is there an echo in here?"};

    public ServerProtocol() {
        super();
        this.state = WAITING;
    }

    public void listen(int port) throws IOException {
        socket = new ServerSocket(4444);
        System.out.printf("Server is started on port %d%n", port);
    }

    // creates a Protocol instance to handle each new client connection
    public ServerProtocol waitsThreaded() throws IOException {
        Socket clientSocket = socket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));
        ServerProtocol kks = new ServerProtocol();
        kks.in = in;
        kks.out = out;
        return kks;
    }

    // exchange data with a given client
    public void exchange() {
        String inputLine, outputLine;
        try {
            // sends the initial message
            outputLine = processInput(null);
            outputLine = createNewMessage(outputLine);
            send(outputLine);
            // proceed until end-of-game
            while (true) {
                // read next input
                inputLine = read();
                // process input
                if (inputLine != null) {
                    outputLine = processInput(inputLine);
                    if (outputLine != null) {
                        // new response
                        outputLine = createNewMessage(outputLine);
                        // send response
                        send(outputLine);
                    }
                }
            }
        } catch (EOPException e) {
            // end-of-game
            // send Bye.
            send(createNewMessage(e.getMessage()));
        }
    }

    // threaded exchange
    public void exchangeThreaded() {
        while (true) {
            try {
                // waits
                ServerProtocol protocol = waitsThreaded();
                // creates a new thread to handle client connection
                Thread t = new Thread(protocol);
                t.start();
                System.out.println("Started thread " + t.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // implements Runnable
    public void run() {
        exchange();
    }

    protected int getState() {
        return this.state;
    }

    // setter/getter methods for state
    protected void setState(int state) {
        this.state = state;
    }

    // overrides super-class method
    public String processInput(String theInput) throws EOPException {
        String theOutput = null;
        int state = getState();

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            setState(SENTKNOCKKNOCK);
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                setState(SENTCLUE);
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! "
                        + "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                setState(ANOTHER);
            } else {
                theOutput = "You're supposed to say \"" + clues[currentJoke]
                        + " who?\"" + "! Try again. Knock! Knock!";
                setState(SENTKNOCKKNOCK);
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                setState(SENTKNOCKKNOCK);
            } else {
                // theOutput = "Bye.";
                setState(WAITING);
                throw new EOPException("Bye.");
            }
        }
        return theOutput;
    }

    /**
     * @effects releases <code>this</code>.
     */
    public void close() {
        super.close();
        try {
            socket.close();
        } catch (Exception e) {
            //
        }
        setState(WAITING);
    }
}