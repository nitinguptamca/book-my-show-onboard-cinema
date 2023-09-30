package book.my.show.cinema.service.domain.exception;


import book.my.show.booking.service.domain.exception.DomainException;

public class CinemaApplicationServiceException extends DomainException {
    public CinemaApplicationServiceException(String message) {
        super(message);
    }

    public CinemaApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
