package lects.lect11.knockknock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Protocol {
    protected PrintWriter out = null;
    protected BufferedReader in = null;

    public Protocol() {

    }

    /**
     * @effects exchange data via in/out
     */
    public abstract void exchange();

    /**
     * @effects returns a standard request message for <code>mesg</code>
     */
    public String createNewMessage(String mesg) {
        return mesg;
    }

    /**
     * @effects If <code>in.readLine()</code> executes then returns result as the
     * next response, else returns <code>null</code>.
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
     * @effects Sends <code>mesg</code> and returns a response from the server.
     * @modifies modifies <code>out</code> with <code>mesg</code>.
     */
    public void send(String mesg) {
        out.println(mesg);
    }

    /**
     * @effects if <code>theInput</code> is valid then returns next response and
     * advances <code>state</code>, else if <code>theInput</code> is "N"
     * then throws <code>EOGException</code>, else returns a help message
     * and resets <code>state</code>.
     */
    public abstract String processInput(String theInput) throws EOPException;

    /**
     * @effects releases <code>this</code>.
     */
    public void close() {
        //
        try {
            out.close();
            in.close();
        } catch (Exception e) {
            //
        }
    }
}
