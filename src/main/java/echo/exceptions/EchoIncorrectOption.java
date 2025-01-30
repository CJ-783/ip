package echo.exceptions;

/**
 * An exception that is thrown when an incorrect option is provided.
 */
public class EchoIncorrectOption extends Exception {

    /**
     * Constructs a new EchoIncorrectOption exception.
     */
    public EchoIncorrectOption() {

    }

    /**
     * Constructs a new EchoIncorrectOption exception with a message.
     *
     * @param msg The message.
     */
    public EchoIncorrectOption(String msg) {
        super(msg);
    }
}
