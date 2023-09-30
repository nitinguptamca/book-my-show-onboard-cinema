package book.my.show.booking.service.domain.ports.output.message.publisher.payment;

import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;


public interface BookingCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<BookingCancelledEvent> {
}
