package echo.exceptions;

/**
 * An exception that is thrown when an incorrect option is provided.
 */
public class EchoIncorrectOption extends Exception {

    /**
     * Constructs a new EchoIncorrectOption exception.
     */
    public EchoIncorrectOption() {
        super("Incorrect option");
    }


}
