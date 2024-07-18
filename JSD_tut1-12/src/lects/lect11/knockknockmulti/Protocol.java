package lects.lect11.knockknockmulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Protocol {
    protected PrintWriter out;
    protected BufferedReader in;

    public Protocol() {
        //
    }

    /**
     * @effects exchange data via in/out
     */
    public abstract void exchange();

    /**
     * @effects returns a standard message for <code>mesg</code>
     */
    public String createNewMessage(String mesg) {
        return mesg;
    }

    /**
     * @effects Reads next line from <code>in</code>, otherwise returns <code>null</code>.
     */
    public String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Protocol.read: failed to read from stream");
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
     * @effects processes <code>theInput</code> to return a response,
     * throws <code>EOPException</code> if end-of-program is reached.
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
