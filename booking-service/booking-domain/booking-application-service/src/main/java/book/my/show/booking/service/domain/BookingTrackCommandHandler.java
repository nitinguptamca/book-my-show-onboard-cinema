package book.my.show.booking.service.domain;



import book.my.show.booking.service.domain.dto.track.TrackBookingQuery;
import book.my.show.booking.service.domain.dto.track.TrackBookingResponse;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.exception.BookingNotFoundException;
import book.my.show.booking.service.domain.mapper.BookingDataMapper;
import book.my.show.booking.service.domain.ports.output.repository.BookingRepository;
import book.my.show.booking.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class BookingTrackCommandHandler {

    private final BookingDataMapper bookingDataMapper;

    private final BookingRepository bookingRepository;

    public BookingTrackCommandHandler(BookingDataMapper bookingDataMapper, BookingRepository bookingRepository) {
        this.bookingDataMapper = bookingDataMapper;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(readOnly = true)
    public TrackBookingResponse trackBooking(TrackBookingQuery trackBookingQuery) {
           Optional<Booking> bookingResult =
                   bookingRepository.findByTrackingId(new TrackingId(trackBookingQuery.getBookingTrackingId()));
           if (bookingResult.isEmpty()) {
               log.warn("Could not find booking with tracking id: {}", trackBookingQuery.getBookingTrackingId());
               throw new BookingNotFoundException("Could not find booking with tracking id: " +
                       trackBookingQuery.getBookingTrackingId());
           }
           return bookingDataMapper.bookingToTrackBookingResponse(bookingResult.get());
    }
}
