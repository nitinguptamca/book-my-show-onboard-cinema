package book.my.show.payment.service.messaging.mapper;


import book.my.show.booking.service.domain.valueobject.PaymentBookingStatus;
import book.my.show.kafka.booking.avro.model.PaymentRequestAvroModel;
import book.my.show.kafka.booking.avro.model.PaymentResponseAvroModel;
import book.my.show.kafka.booking.avro.model.PaymentStatus;
import book.my.show.payment.service.domain.dto.PaymentRequest;
import book.my.show.payment.service.domain.event.PaymentCancelledEvent;
import book.my.show.payment.service.domain.event.PaymentCompletedEvent;
import book.my.show.payment.service.domain.event.PaymentFailedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentResponseAvroModel
    paymentCompletedEventToPaymentResponseAvroModel(PaymentCompletedEvent paymentCompletedEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setPaymentId(paymentCompletedEvent.getPayment().getId().getValue().toString())
                .setCustomerId(paymentCompletedEvent.getPayment().getCustomerId().getValue().toString())
                .setBookingId(paymentCompletedEvent.getPayment().getBookingId().getValue().toString())
                .setPrice(paymentCompletedEvent.getPayment().getPrice().getAmount())
                .setCreatedAt(paymentCompletedEvent.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(paymentCompletedEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages(paymentCompletedEvent.getFailureMessages())
                .build();
    }

    public PaymentResponseAvroModel
    paymentCancelledEventToPaymentResponseAvroModel(PaymentCancelledEvent paymentCancelledEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setPaymentId(paymentCancelledEvent.getPayment().getId().getValue().toString())
                .setCustomerId(paymentCancelledEvent.getPayment().getCustomerId().getValue().toString())
                .setBookingId(paymentCancelledEvent.getPayment().getBookingId().getValue().toString())
                .setPrice(paymentCancelledEvent.getPayment().getPrice().getAmount())
                .setCreatedAt(paymentCancelledEvent.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(paymentCancelledEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages(paymentCancelledEvent.getFailureMessages())
                .build();
    }

    public PaymentResponseAvroModel
    paymentFailedEventToPaymentResponseAvroModel(PaymentFailedEvent paymentFailedEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setPaymentId(paymentFailedEvent.getPayment().getId().getValue().toString())
                .setCustomerId(paymentFailedEvent.getPayment().getCustomerId().getValue().toString())
                .setBookingId(paymentFailedEvent.getPayment().getBookingId().getValue().toString())
                .setPrice(paymentFailedEvent.getPayment().getPrice().getAmount())
                .setCreatedAt(paymentFailedEvent.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(paymentFailedEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages(paymentFailedEvent.getFailureMessages())
                .build();
    }

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .id(paymentRequestAvroModel.getId())
                .sagaId(paymentRequestAvroModel.getSagaId())
                .customerId(paymentRequestAvroModel.getCustomerId())
                .bookingId(paymentRequestAvroModel.getBookingId())
                .price(paymentRequestAvroModel.getPrice())
                .createdAt(paymentRequestAvroModel.getCreatedAt())
                .paymentBookingStatus(PaymentBookingStatus.valueOf(paymentRequestAvroModel.getPaymentBookingStatus().name()))
                .build();
    }
}
