package book.my.show.payment.service.domain.ports.output.message.publisher;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.payment.service.domain.event.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}
