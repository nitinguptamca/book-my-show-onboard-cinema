package book.my.show.cinema.service.domain.exception;


import book.my.show.booking.service.domain.exception.DomainException;

public class CinaemaDomainException extends DomainException {
    public CinaemaDomainException(String message) {
        super(message);
    }

    public CinaemaDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
