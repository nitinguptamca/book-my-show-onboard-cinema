package book.my.show.payment.service.domain;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.payment.service.domain.entity.CreditEntry;
import book.my.show.payment.service.domain.entity.CreditHistory;
import book.my.show.payment.service.domain.entity.Payment;
import book.my.show.payment.service.domain.event.PaymentCancelledEvent;
import book.my.show.payment.service.domain.event.PaymentCompletedEvent;
import book.my.show.payment.service.domain.event.PaymentEvent;
import book.my.show.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
