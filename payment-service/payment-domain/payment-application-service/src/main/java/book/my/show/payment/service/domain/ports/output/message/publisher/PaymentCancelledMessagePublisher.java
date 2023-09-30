package book.my.show.payment.service.domain.ports.output.message.publisher;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
