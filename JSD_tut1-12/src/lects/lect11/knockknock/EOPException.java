package lects.lect11.knockknock;

/**
 * End-of-game exception is thrown to signal the end-of-game.
 */
public class EOPException extends Exception {
    public EOPException(String msg) {
        super(msg);
    }
}
