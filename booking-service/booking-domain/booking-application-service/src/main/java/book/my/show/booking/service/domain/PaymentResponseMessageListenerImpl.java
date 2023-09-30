package book.my.show.booking.service.domain;

import book.my.show.booking.service.domain.dto.message.PaymentResponse;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static book.my.show.booking.service.domain.constants.DomainConstants.FAILURE_MESSAGE_DELIMITER;


@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final BookingPaymentSaga bookingPaymentSaga;

    public PaymentResponseMessageListenerImpl(BookingPaymentSaga bookingPaymentSaga) {
        this.bookingPaymentSaga = bookingPaymentSaga;
    }

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        BookingPaidEvent domainEvent = bookingPaymentSaga.process(paymentResponse);
        log.info("Publishing BookingPaidEvent for booking id: {}", paymentResponse.getBookingId());
        domainEvent.fire();
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        bookingPaymentSaga.rollback(paymentResponse);
        log.info("Booking is roll backed for booking id: {} with failure messages: {}",
                paymentResponse.getBookingId(),
                String.join(FAILURE_MESSAGE_DELIMITER, paymentResponse.getFailureMessages()));
    }
}
