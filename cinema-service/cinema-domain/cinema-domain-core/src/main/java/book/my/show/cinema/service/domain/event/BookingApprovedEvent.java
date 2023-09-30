package book.my.show.cinema.service.domain.event;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.cinema.service.domain.entity.BookingApproval;

import java.time.ZonedDateTime;
import java.util.List;

public class BookingApprovedEvent extends BookingApprovalEvent {

    private final DomainEventPublisher<BookingApprovedEvent> bookingApprovedEventDomainEventPublisher;

    public BookingApprovedEvent(BookingApproval bookingApproval,
                                CinemaId cinemaId,
                                List<String> failureMessages,
                                ZonedDateTime createdAt,
                                DomainEventPublisher<BookingApprovedEvent> bookingApprovedEventDomainEventPublisher) {
        super(bookingApproval, cinemaId, failureMessages, createdAt);
        this.bookingApprovedEventDomainEventPublisher = bookingApprovedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        bookingApprovedEventDomainEventPublisher.publish(this);
    }
}
