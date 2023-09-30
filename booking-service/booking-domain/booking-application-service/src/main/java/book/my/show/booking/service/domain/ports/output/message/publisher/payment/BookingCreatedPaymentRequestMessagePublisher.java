package book.my.show.booking.service.domain.ports.output.message.publisher.payment;

import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;


public interface BookingCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<BookingCreatedEvent> {
}
