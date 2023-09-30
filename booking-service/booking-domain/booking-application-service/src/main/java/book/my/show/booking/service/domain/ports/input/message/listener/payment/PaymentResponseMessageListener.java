package book.my.show.booking.service.domain.ports.input.message.listener.payment;

import book.my.show.booking.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
