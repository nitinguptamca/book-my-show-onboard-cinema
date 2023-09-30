package book.my.show.booking.service.domain.exception;

public class BookingDomainException extends DomainException{
    public BookingDomainException() {
    }

    public BookingDomainException(String message) {
        super(message);
    }

    public BookingDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
