package none.cgutils.recipe.paneer.tikka.exception;

public class PaprikaNotFoundException extends RuntimeException {
    public PaprikaNotFoundException() {
    }

    public PaprikaNotFoundException(String message) {
        super(message);
    }

    public PaprikaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaprikaNotFoundException(Throwable cause) {
        super(cause);
    }

    public PaprikaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
