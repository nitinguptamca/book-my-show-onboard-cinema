package book.my.show.dataaccess.cinema.exception;

public class CinemaDataAccessException extends RuntimeException{
    public CinemaDataAccessException() {
    }

    public CinemaDataAccessException(String message) {
        super(message);
    }

    public CinemaDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CinemaDataAccessException(Throwable cause) {
        super(cause);
    }

    public CinemaDataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
