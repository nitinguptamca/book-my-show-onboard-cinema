package book.my.show.booking.service.domain.ports.input.service;

import book.my.show.booking.service.domain.dto.track.TrackBookingQuery;
import book.my.show.booking.service.domain.dto.track.TrackBookingResponse;
import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.dto.create.CreateBookingResponse;
import jakarta.validation.Valid;


public interface BookingApplicationService {

    CreateBookingResponse createBooking(@Valid CreateBookingCommand createBookingCommand);

    TrackBookingResponse trackBooking(@Valid TrackBookingQuery trackBookingQuery);
}
