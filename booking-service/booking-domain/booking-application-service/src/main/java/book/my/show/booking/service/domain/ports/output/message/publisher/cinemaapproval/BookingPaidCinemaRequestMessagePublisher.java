package book.my.show.booking.service.domain.ports.output.message.publisher.cinemaapproval;

import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;


public interface BookingPaidCinemaRequestMessagePublisher extends DomainEventPublisher<BookingPaidEvent> {
}
