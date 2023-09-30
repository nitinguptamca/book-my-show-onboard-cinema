package book.my.show.booking.service.domain;



import book.my.show.booking.service.domain.dto.message.PaymentResponse;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.event.EmptyEvent;
import book.my.show.booking.service.domain.exception.BookingNotFoundException;
import book.my.show.booking.service.domain.ports.output.message.publisher.cinemaapproval.BookingPaidCinemaRequestMessagePublisher;
import book.my.show.booking.service.domain.ports.output.repository.BookingRepository;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class BookingPaymentSaga implements SagaStep<PaymentResponse, BookingPaidEvent, EmptyEvent> {

    private final BookingDomainService bookingDomainService;
    private final BookingRepository bookingRepository;
    private final BookingPaidCinemaRequestMessagePublisher bookingPaidCinemaRequestMessagePublisher;

    public BookingPaymentSaga(BookingDomainService bookingDomainService,
                              BookingRepository bookingRepository,
                              BookingPaidCinemaRequestMessagePublisher bookingPaidCinemaRequestMessagePublisher) {
        this.bookingDomainService = bookingDomainService;
        this.bookingRepository = bookingRepository;
        this.bookingPaidCinemaRequestMessagePublisher = bookingPaidCinemaRequestMessagePublisher;
    }

    @Override
    @Transactional
    public BookingPaidEvent process(PaymentResponse paymentResponse) {
        log.info("Completing payment for booking with id: {}", paymentResponse.getBookingId());
        Booking booking = findBooking(paymentResponse.getBookingId());
        BookingPaidEvent domainEvent = bookingDomainService.payBooking(booking, bookingPaidCinemaRequestMessagePublisher);
        bookingRepository.save(booking);
        log.info("Booking with id: {} is paid", booking.getId().getValue());
        return domainEvent;
    }

    @Override
    @Transactional
    public EmptyEvent rollback(PaymentResponse paymentResponse) {
        log.info("Cancelling booking with id: {}", paymentResponse.getBookingId());
        Booking booking = findBooking(paymentResponse.getBookingId());
        bookingDomainService.cancelBooking(booking, paymentResponse.getFailureMessages());
        bookingRepository.save(booking);
        log.info("Booking with id: {} is cancelled", booking.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    private Booking findBooking(String bookingId) {
        Optional<Booking> bookingResponse = bookingRepository.findById(new BookingId(UUID.fromString(bookingId)));
        if (bookingResponse.isEmpty()) {
            log.error("Booking with id: {} could not be found!", bookingId);
            throw new BookingNotFoundException("Booking with id " + bookingId + " could not be found!");
        }
        return bookingResponse.get();
    }
}
