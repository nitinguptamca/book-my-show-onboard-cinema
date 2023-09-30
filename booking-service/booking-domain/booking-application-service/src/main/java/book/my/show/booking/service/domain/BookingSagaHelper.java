package book.my.show.booking.service.domain;

import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.exception.BookingNotFoundException;
import book.my.show.booking.service.domain.ports.output.repository.BookingRepository;
import book.my.show.booking.service.domain.valueobject.BookingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class BookingSagaHelper {

    private final BookingRepository bookingRepository;

    public BookingSagaHelper(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    Booking findBooking(String bookingId) {
        Optional<Booking> bookingResponse = bookingRepository.findById(new BookingId(UUID.fromString(bookingId)));
        if (bookingResponse.isEmpty()) {
            log.error("Booking with id: {} could not be found!", bookingId);
            throw new BookingNotFoundException("Booking with id " + bookingId + " could not be found!");
        }
        return bookingResponse.get();
    }

    void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
}
