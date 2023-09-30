package book.my.show.payment.service.domain.mapper;


import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.payment.service.domain.dto.PaymentRequest;
import book.my.show.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .bookingId(new BookingId(UUID.fromString(paymentRequest.getBookingId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
