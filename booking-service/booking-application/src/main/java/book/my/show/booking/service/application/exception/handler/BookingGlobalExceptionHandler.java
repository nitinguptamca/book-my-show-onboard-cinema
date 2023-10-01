package book.my.show.booking.service.application.exception.handler;


import book.my.show.application.handler.ErrorDTO;
import book.my.show.application.handler.GlobalExceptionHandler;
import book.my.show.booking.service.domain.exception.BookingDomainException;
import book.my.show.booking.service.domain.exception.BookingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class BookingGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {BookingDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(BookingDomainException bookingDomainException) {
        log.error(bookingDomainException.getMessage(), bookingDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(bookingDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {BookingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(BookingNotFoundException bookingNotFoundException) {
        log.error(bookingNotFoundException.getMessage(), bookingNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(bookingNotFoundException.getMessage())
                .build();
    }
}
