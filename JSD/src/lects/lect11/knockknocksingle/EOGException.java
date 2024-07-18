package lects.lect11.knockknocksingle;

/**
 * End-of-game exception is thrown to signal the end-of-game.
 */
public class EOGException extends Exception {
    public EOGException(String msg) {
        super(msg);
    }
}
