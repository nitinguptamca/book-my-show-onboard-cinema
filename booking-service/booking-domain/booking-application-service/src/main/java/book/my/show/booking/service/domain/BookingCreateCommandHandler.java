package book.my.show.booking.service.domain;


import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.dto.create.CreateBookingResponse;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.mapper.BookingDataMapper;
import book.my.show.booking.service.domain.ports.output.message.publisher.payment.BookingCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingCreateCommandHandler {

    private final BookingCreateHelper bookingCreateHelper;

    private final BookingDataMapper bookingDataMapper;

    private final BookingCreatedPaymentRequestMessagePublisher bookingCreatedPaymentRequestMessagePublisher;

    public BookingCreateCommandHandler(BookingCreateHelper bookingCreateHelper,
                                       BookingDataMapper bookingDataMapper,
                                       BookingCreatedPaymentRequestMessagePublisher bookingCreatedPaymentRequestMessagePublisher) {
        this.bookingCreateHelper = bookingCreateHelper;
        this.bookingDataMapper = bookingDataMapper;
        this.bookingCreatedPaymentRequestMessagePublisher = bookingCreatedPaymentRequestMessagePublisher;
    }

    public CreateBookingResponse createBooking(CreateBookingCommand createBookingCommand) {
        BookingCreatedEvent bookingCreatedEvent = bookingCreateHelper.persistBooking(createBookingCommand);
        log.info("Booking is created with id: {}", bookingCreatedEvent.getBooking().getId().getValue());
        bookingCreatedPaymentRequestMessagePublisher.publish(bookingCreatedEvent);
        return bookingDataMapper.bookingToCreateBookingResponse(bookingCreatedEvent.getBooking(),
                "Booking created successfully");
    }
}
