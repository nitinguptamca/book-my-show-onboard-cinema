package book.my.show.booking.service.domain.event;

import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;


import java.time.ZonedDateTime;

public class BookingCancelledEvent extends BookingEvent {

    private final DomainEventPublisher<BookingCancelledEvent> bookingCancelledEventDomainEventPublisher;

    public BookingCancelledEvent(Booking booking,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<BookingCancelledEvent> bookingCancelledEventDomainEventPublisher) {
        super(booking, createdAt);
        this.bookingCancelledEventDomainEventPublisher = bookingCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        bookingCancelledEventDomainEventPublisher.publish(this);
    }
}
