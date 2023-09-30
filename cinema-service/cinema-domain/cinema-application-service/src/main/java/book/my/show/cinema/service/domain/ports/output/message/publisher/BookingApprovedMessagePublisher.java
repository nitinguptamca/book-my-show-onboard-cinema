package book.my.show.cinema.service.domain.ports.output.message.publisher;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.cinema.service.domain.event.BookingApprovalEvent;
import book.my.show.cinema.service.domain.event.BookingApprovedEvent;

public interface BookingApprovedMessagePublisher extends DomainEventPublisher<BookingApprovedEvent> {
}
