package book.my.show.booking.service.domain;

import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.dto.create.CreateBookingResponse;
import book.my.show.booking.service.domain.dto.track.TrackBookingQuery;
import book.my.show.booking.service.domain.dto.track.TrackBookingResponse;
import book.my.show.booking.service.domain.ports.input.service.BookingApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class BookingApplicationServiceImpl implements BookingApplicationService {

    private final BookingCreateCommandHandler bookingCreateCommandHandler;

    private final BookingTrackCommandHandler bookingTrackCommandHandler;

    public BookingApplicationServiceImpl(BookingCreateCommandHandler bookingCreateCommandHandler,
                                         BookingTrackCommandHandler bookingTrackCommandHandler) {
        this.bookingCreateCommandHandler = bookingCreateCommandHandler;
        this.bookingTrackCommandHandler = bookingTrackCommandHandler;
    }

    @Override
    public CreateBookingResponse createBooking(CreateBookingCommand createBookingCommand) {
        return bookingCreateCommandHandler.createBooking(createBookingCommand);
    }

    @Override
    public TrackBookingResponse trackBooking(TrackBookingQuery trackBookingQuery) {
        return bookingTrackCommandHandler.trackBooking(trackBookingQuery);
    }
}
