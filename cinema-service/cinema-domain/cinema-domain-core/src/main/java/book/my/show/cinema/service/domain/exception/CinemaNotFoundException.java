package book.my.show.cinema.service.domain.exception;


import book.my.show.booking.service.domain.exception.DomainException;

public class CinemaNotFoundException extends DomainException {
    public CinemaNotFoundException(String message) {
        super(message);
    }

    public CinemaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
