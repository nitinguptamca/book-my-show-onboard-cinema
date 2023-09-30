package book.my.show.booking.service.domain.event;

import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;


import java.time.ZonedDateTime;

public class BookingCreatedEvent extends BookingEvent {

    private final DomainEventPublisher<BookingCreatedEvent> bookingCreatedEventDomainEventPublisher;

    public BookingCreatedEvent(Booking booking,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<BookingCreatedEvent> bookingCreatedEventDomainEventPublisher) {
        super(booking, createdAt);
        this.bookingCreatedEventDomainEventPublisher = bookingCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        bookingCreatedEventDomainEventPublisher.publish(this);
    }
}
