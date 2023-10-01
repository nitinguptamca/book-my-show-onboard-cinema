package book.my.show.booking.service.messaging.mapper;



import book.my.show.booking.service.domain.dto.message.CinemaApprovalResponse;
import book.my.show.booking.service.domain.dto.message.PaymentResponse;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.kafka.booking.avro.model.CinemaApprovalRequestAvroModel;
import book.my.show.kafka.booking.avro.model.CinemaApprovalResponseAvroModel;
import book.my.show.kafka.booking.avro.model.CinemaBookingStatus;
import book.my.show.kafka.booking.avro.model.PaymentBookingStatus;
import book.my.show.kafka.booking.avro.model.PaymentRequestAvroModel;
import book.my.show.kafka.booking.avro.model.PaymentResponseAvroModel;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookingMessagingDataMapper {

    public PaymentRequestAvroModel bookingCreatedEventToPaymentRequestAvroModel(BookingCreatedEvent bookingCreatedEvent) {
        Booking booking = bookingCreatedEvent.getBooking();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(booking.getCustomerId().getValue().toString())
                .setBookingId(booking.getId().getValue().toString())
                .setPrice(booking.getPrice().getAmount())
                .setCreatedAt(bookingCreatedEvent.getCreatedAt().toInstant())
                .setPaymentBookingStatus(PaymentBookingStatus.PENDING)
                .build();
    }

    public PaymentRequestAvroModel bookingCancelledEventToPaymentRequestAvroModel(BookingCancelledEvent bookingCancelledEvent) {
        Booking booking = bookingCancelledEvent.getBooking();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(booking.getCustomerId().getValue().toString())
                .setBookingId(booking.getId().getValue().toString())
                .setPrice(booking.getPrice().getAmount())
                .setCreatedAt(bookingCancelledEvent.getCreatedAt().toInstant())
                .setPaymentBookingStatus(PaymentBookingStatus.CANCELLED)
                .build();
    }

    public CinemaApprovalRequestAvroModel
    bookingPaidEventToCinemaApprovalRequestAvroModel(BookingPaidEvent bookingPaidEvent) {
        Booking booking = bookingPaidEvent.getBooking();
        return CinemaApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setBookingId(booking.getId().getValue().toString())
                .setCinemaId(booking.getcinemaId().getValue().toString())
                .setBookingId(booking.getId().getValue().toString())
                .setCinemaBookingStatus(book.my.show.kafka.booking.avro.model.CinemaBookingStatus
                        .valueOf(booking.getBookingStatus().name()))
                .setMovies(booking.getItems().stream().map(bookingItem ->
                        book.my.show.kafka.booking.avro.model.Movie.newBuilder()
                                .setId(bookingItem.getMovie().getId().getValue().toString())
                                .setQuantity(bookingItem.getQuantity())
                                .setMovieTime(bookingItem.getMovieTime())
                                .setSeatNumber(bookingItem.getSeatNumber())
                                .build()).collect(Collectors.toList()))
                .setPrice(booking.getPrice().getAmount())
                .setCreatedAt(bookingPaidEvent.getCreatedAt().toInstant())
                .setCinemaBookingStatus(CinemaBookingStatus.PAID)
                .build();
    }

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel
                                                                             paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .id(paymentResponseAvroModel.getId())
                .sagaId(paymentResponseAvroModel.getSagaId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .customerId(paymentResponseAvroModel.getCustomerId())
                .bookingId(paymentResponseAvroModel.getBookingId())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .paymentStatus(book.my.show.booking.service.domain.valueobject.PaymentStatus.valueOf(
                        paymentResponseAvroModel.getPaymentStatus().name()))
                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public CinemaApprovalResponse
    approvalResponseAvroModelToApprovalResponse(CinemaApprovalResponseAvroModel
                                                        cinemaApprovalResponseAvroModel) {
        return CinemaApprovalResponse.builder()
                .id(cinemaApprovalResponseAvroModel.getId())
                .sagaId(cinemaApprovalResponseAvroModel.getSagaId())
                .cinemaId(cinemaApprovalResponseAvroModel.getCinemaId())
                .bookingId(cinemaApprovalResponseAvroModel.getBookingId())
                .createdAt(cinemaApprovalResponseAvroModel.getCreatedAt())
                .bookingApprovalStatus(book.my.show.booking.service.domain.valueobject.BookingApprovalStatus.valueOf(
                        cinemaApprovalResponseAvroModel.getBookingApprovalStatus().name()))
                .failureMessages(cinemaApprovalResponseAvroModel.getFailureMessages())
                .build();
    }
}
