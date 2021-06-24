package dreamTeam.global_exception;

public class IncorrectMailException extends RuntimeException {

    public IncorrectMailException(String message) {
        super(message);
    }

}