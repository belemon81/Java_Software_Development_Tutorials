/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package lects.lect11.knockknocksingle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Protocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;
    private BufferedReader in;
    private PrintWriter out;
    private int state;
    private int currentJoke = 0;

    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here!",
            "I didn't know you could yodel!", "Bless you!",
            "Is there an owl in here?", "Is there an echo in here?"};

    private BufferedReader stdIn;

    public Protocol(BufferedReader in, PrintWriter out) {
        //
        this.in = in;
        this.out = out;
        state = WAITING;
    }

    /**
     * @effects returns a standard request message for <code>mesg</code>
     */
    public String createNewMessage(String mesg) {
        return mesg;
    }

    /**
     * @effects If <code>in.readLine()</code> executes then returns result as the
     * next response,
     * else returns <code>null</code>.
     */
    public String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("KKP.readResponse: failed to read response");
        }
        return null;
    }

    /**
     * @effects Sends <code>mesg</code> and returns
     * a response from the server.
     * @modifies modifies <code>out</code> with <code>mesg</code>.
     */
    public void send(String mesg) {
        out.println(mesg);
    }

    /**
     * @effects If <code>fromServer != "Bye".</code> returns the next user's
     * keyboard input,
     * else throws <code>EOGException</code>.
     */
    public String processServerInput(String fromServer) throws EOGException {
        String fromUser = null;

        if (fromServer.equals("Bye."))
            throw new EOGException("Bye.");

        try {
            if (stdIn == null) {
                stdIn = new BufferedReader(new InputStreamReader(System.in));
            }

            fromUser = stdIn.readLine();

        } catch (IOException e) {
            System.err.println("KKP.newRequest: Failed to get user input");
        }

        return fromUser;
    }

    /**
     * @effects if <code>theInput</code> is valid then returns next response and
     * advances <code>state</code>, else if <code>theInput</code> is "N"
     * then throws <code>EOGException</code>, else returns a help message
     * and resets <code>state</code>.
     */
    public String processClientInput(String theInput) throws EOGException {
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
                throw new EOGException("Bye.");
            }
        }
        return theOutput;
    }

    /**
     * @effects releases <code>this</code>.
     */
    public void close() {
        try {

            if (stdIn != null)
                stdIn.close();

            in.close();
            out.close();

        } catch (Exception e) {
            // ignore
        }
    }
}
