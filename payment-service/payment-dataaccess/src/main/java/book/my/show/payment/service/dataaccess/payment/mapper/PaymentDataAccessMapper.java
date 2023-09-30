package book.my.show.payment.service.dataaccess.payment.mapper;


import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.payment.service.dataaccess.payment.entity.PaymentEntity;
import book.my.show.payment.service.domain.entity.Payment;
import book.my.show.payment.service.domain.valueobject.PaymentId;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId().getValue())
                .customerId(payment.getCustomerId().getValue())
                .bookingId(payment.getBookingId().getValue())
                .price(payment.getPrice().getAmount())
                .status(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(new PaymentId(paymentEntity.getId()))
                .customerId(new CustomerId(paymentEntity.getCustomerId()))
                .bookingId(new BookingId(paymentEntity.getBookingId()))
                .price(new Money(paymentEntity.getPrice()))
                .createdAt(paymentEntity.getCreatedAt())
                .build();
    }

}
