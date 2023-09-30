package book.my.show.booking.service.domain.exception;



public class BookingNotFoundException extends DomainException {

    public BookingNotFoundException(String message) {
        super(message);
    }

    public BookingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
