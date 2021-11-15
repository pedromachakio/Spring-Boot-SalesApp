package pedromachakio.com.github.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException() {
        super("Request not found bro.");
    }
}
