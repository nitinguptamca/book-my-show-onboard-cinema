package book.my.show.booking.service.domain;


import book.my.show.booking.service.domain.dto.message.CinemaApprovalResponse;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.event.EmptyEvent;
import book.my.show.booking.service.domain.ports.output.message.publisher.payment.BookingCancelledPaymentRequestMessagePublisher;
import book.my.show.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class BookingApprovalSaga implements SagaStep<CinemaApprovalResponse, EmptyEvent, BookingCancelledEvent> {

    private final BookingDomainService bookingDomainService;
    private final BookingSagaHelper bookingSagaHelper;
    private final BookingCancelledPaymentRequestMessagePublisher bookingCancelledPaymentRequestMessagePublisher;

    public BookingApprovalSaga(BookingDomainService bookingDomainService, 
                               BookingSagaHelper bookingSagaHelper, 
                               BookingCancelledPaymentRequestMessagePublisher bookingCancelledPaymentRequestMessagePublisher) {
        this.bookingDomainService = bookingDomainService;
        this.bookingSagaHelper = bookingSagaHelper;
        this.bookingCancelledPaymentRequestMessagePublisher = bookingCancelledPaymentRequestMessagePublisher;
    }


    @Override
    @Transactional
    public EmptyEvent process(CinemaApprovalResponse cinemaApprovalResponse) {
        log.info("Approving booking with id: {}", cinemaApprovalResponse.getBookingId());
        Booking booking = bookingSagaHelper.findBooking(cinemaApprovalResponse.getBookingId());
        bookingDomainService.approveBooking(booking);
        bookingSagaHelper.saveBooking(booking);
        log.info("Booking with id: {} is approved", booking.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    @Override
    @Transactional
    public BookingCancelledEvent rollback(CinemaApprovalResponse cinemaApprovalResponse) {
        log.info("Cancelling booking with id: {}", cinemaApprovalResponse.getBookingId());
        Booking booking = bookingSagaHelper.findBooking(cinemaApprovalResponse.getBookingId());
        BookingCancelledEvent domainEvent = bookingDomainService.cancelBookingPayment(booking,
                cinemaApprovalResponse.getFailureMessages(),
                bookingCancelledPaymentRequestMessagePublisher);
        bookingSagaHelper.saveBooking(booking);
        log.info("Booking with id: {} is cancelling", booking.getId().getValue());
        return domainEvent;
    }
}
