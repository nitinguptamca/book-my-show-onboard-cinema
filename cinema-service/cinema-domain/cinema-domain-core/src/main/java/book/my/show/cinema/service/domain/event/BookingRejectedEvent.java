package book.my.show.cinema.service.domain.event;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.cinema.service.domain.entity.BookingApproval;

import java.time.ZonedDateTime;
import java.util.List;

public class BookingRejectedEvent extends BookingApprovalEvent {

    private final DomainEventPublisher<BookingRejectedEvent> bookingRejectedEventDomainEventPublisher;

    public BookingRejectedEvent(BookingApproval bookingApproval,
                                CinemaId cinemaId,
                                List<String> failureMessages,
                                ZonedDateTime createdAt,
                                DomainEventPublisher<BookingRejectedEvent> bookingRejectedEventDomainEventPublisher) {
        super(bookingApproval, cinemaId, failureMessages, createdAt);
        this.bookingRejectedEventDomainEventPublisher = bookingRejectedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        bookingRejectedEventDomainEventPublisher.publish(this);
    }
}
