package book.my.show.booking.service.domain;



import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.entity.Cinema;
import book.my.show.booking.service.domain.entity.Customer;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.exception.BookingDomainException;
import book.my.show.booking.service.domain.mapper.BookingDataMapper;
import book.my.show.booking.service.domain.ports.output.message.publisher.payment.BookingCreatedPaymentRequestMessagePublisher;
import book.my.show.booking.service.domain.ports.output.repository.BookingRepository;
import book.my.show.booking.service.domain.ports.output.repository.CinemaRepository;
import book.my.show.booking.service.domain.ports.output.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class BookingCreateHelper {

    private final BookingDomainService bookingDomainService;

    private final BookingRepository bookingRepository;

    private final CustomerRepository customerRepository;

    private final CinemaRepository cinemaRepository;

    private final BookingDataMapper bookingDataMapper;

    private final BookingCreatedPaymentRequestMessagePublisher bookingCreatedEventDomainEventPublisher;

    public BookingCreateHelper(BookingDomainService bookingDomainService,
                               BookingRepository bookingRepository,
                               CustomerRepository customerRepository,
                               CinemaRepository cinemaRepository,
                               BookingDataMapper bookingDataMapper,
                               BookingCreatedPaymentRequestMessagePublisher bookingCreatedEventDomainEventPublisher) {
        this.bookingDomainService = bookingDomainService;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.cinemaRepository = cinemaRepository;
        this.bookingDataMapper = bookingDataMapper;
        this.bookingCreatedEventDomainEventPublisher = bookingCreatedEventDomainEventPublisher;
    }

    @Transactional
    public BookingCreatedEvent persistBooking(CreateBookingCommand createBookingCommand) {
        checkCustomer(createBookingCommand.getCustomerId());
        Cinema cinema = checkCinema(createBookingCommand);
        Booking booking = bookingDataMapper.createBookingCommandToBooking(createBookingCommand);
        BookingCreatedEvent bookingCreatedEvent = bookingDomainService.validateAndInitiateBooking(booking, cinema,
                bookingCreatedEventDomainEventPublisher);
        saveBooking(booking);
        log.info("Booking is created with id: {}", bookingCreatedEvent.getBooking().getId().getValue());
        return bookingCreatedEvent;
    }

    private Cinema checkCinema(CreateBookingCommand createBookingCommand) {
        Cinema cinema = bookingDataMapper.createBookingCommandToCinema(createBookingCommand);
        Optional<Cinema> optionalCinema = cinemaRepository.findCinemaInformation(cinema);
        if (optionalCinema.isEmpty()) {
            log.warn("Could not find cinema with cinema id: {}", createBookingCommand.getCinemaId());
            throw new BookingDomainException("Could not find cinema with cinema id: " +
                    createBookingCommand.getCinemaId());
        }
        return optionalCinema.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new BookingDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private Booking saveBooking(Booking booking) {
        Booking bookingResult = bookingRepository.save(booking);
        if (bookingResult == null) {
            log.error("Could not save booking!");
            throw new BookingDomainException("Could not save booking!");
        }
        log.info("Booking is saved with id: {}", bookingResult.getId().getValue());
        return bookingResult;
    }
}
