package book.my.show.booking.service.domain.ports.output.repository;


import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(BookingId bookingId);

    Optional<Booking> findByTrackingId(TrackingId trackingId);
}
