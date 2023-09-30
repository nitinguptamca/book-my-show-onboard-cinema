package book.my.show.booking.service.domain.event;

import book.my.show.booking.service.domain.entity.Booking;


import java.time.ZonedDateTime;

public abstract class BookingEvent implements DomainEvent<Booking> {
    private final Booking booking;
    private final ZonedDateTime createdAt;

    public BookingEvent(Booking booking, ZonedDateTime createdAt) {
        this.booking = booking;
        this.createdAt = createdAt;
    }

    public Booking getBooking() {
        return booking;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
