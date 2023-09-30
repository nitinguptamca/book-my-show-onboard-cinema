package book.my.show.cinema.service.domain;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.cinema.service.domain.entity.Cinema;
import book.my.show.cinema.service.domain.event.BookingApprovalEvent;
import book.my.show.cinema.service.domain.event.BookingApprovedEvent;
import book.my.show.cinema.service.domain.event.BookingRejectedEvent;

import java.util.List;

public interface CinemaDomainService {

    BookingApprovalEvent validateBooking(Cinema cinema,
                                         List<String> failureMessages,
                                         DomainEventPublisher<BookingApprovedEvent> bookingApprovedEventDomainEventPublisher,
                                         DomainEventPublisher<BookingRejectedEvent> bookingRejectedEventDomainEventPublisher);
}
