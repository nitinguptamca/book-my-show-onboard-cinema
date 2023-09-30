package book.my.show.booking.service.domain.event;

import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;

public class BookingPaidEvent extends BookingEvent {

    private final DomainEventPublisher<BookingPaidEvent> bookingPaidEventDomainEventPublisher;

    public BookingPaidEvent(Booking booking,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<BookingPaidEvent> bookingPaidEventDomainEventPublisher) {
        super(booking, createdAt);
        this.bookingPaidEventDomainEventPublisher = bookingPaidEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        bookingPaidEventDomainEventPublisher.publish(this);
    }
}
