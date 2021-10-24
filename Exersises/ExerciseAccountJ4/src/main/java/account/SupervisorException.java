package account;

@SuppressWarnings("serial")
public class SupervisorException extends RuntimeException {

    public SupervisorException() {
        super();
    }

    public SupervisorException(String message) {
        super(message);
    }

    public SupervisorException(Throwable cause) {
        super(cause);
    }

    public SupervisorException(String message, Throwable cause) {
        super(message, cause);
    }
}
