/**
 * A sub-class of Protocol that is used by KnockKnockServer.
 */

package lects.lect11.knockknockmulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerProtocol extends Protocol {

    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;
    private ServerSocket socket;
    // application-specific attributes
    private int state;
    private int currentJoke = 0;

    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here!",
            "I didn't know you could yodel!", "Bless you!",
            "Is there an owl in here?", "Is there an echo in here?"};

    public ServerProtocol() {
        //
        super();
        this.state = WAITING;
    }

    @Override
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

    /**
     * Opens server socket at <code>port</code>.
     */
    public void listen(int port) throws UnknownHostException,
            IOException {
        socket = new ServerSocket(port);
    }

    // changed to remove dependency on socket
    public void waits() throws IOException {
        Socket clientSocket = socket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // overrides super-class method
    public String processInput(String theInput) throws EOPException {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! "
                        + "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + clues[currentJoke]
                        + " who?\"" + "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                // theOutput = "Bye.";
                state = WAITING;
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
    }
}