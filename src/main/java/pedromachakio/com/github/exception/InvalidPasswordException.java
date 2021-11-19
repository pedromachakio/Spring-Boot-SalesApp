package pedromachakio.com.github.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password bro");
    }
}
