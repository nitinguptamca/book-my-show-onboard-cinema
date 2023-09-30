package book.my.show.booking.service.domain;


import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.entity.Cinema;
import book.my.show.booking.service.domain.entity.Movie;
import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.booking.service.domain.exception.BookingDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static book.my.show.booking.service.domain.constants.DomainConstants.UTC;


@Slf4j
public class BookingDomainServiceImpl implements BookingDomainService {

    @Override
    public BookingCreatedEvent validateAndInitiateBooking(Booking booking, Cinema cinema,
                                                          DomainEventPublisher<BookingCreatedEvent>
                                                                bookingCreatedEventDomainEventPublisher) {
        validateCinema(cinema);
        setBookingMovieInformation(booking, cinema);
        booking.validateBooking();
        booking.initializeBooking();
        log.info("Booking with id: {} is initiated", booking.getId().getValue());
        return new BookingCreatedEvent(booking, ZonedDateTime.now(ZoneId.of(UTC)), bookingCreatedEventDomainEventPublisher);
    }

    @Override
    public BookingPaidEvent payBooking(Booking booking,
                                       DomainEventPublisher<BookingPaidEvent> bookingPaidEventDomainEventPublisher) {
        booking.pay();
        log.info("Booking with id: {} is paid", booking.getId().getValue());
        return new BookingPaidEvent(booking, ZonedDateTime.now(ZoneId.of(UTC)), bookingPaidEventDomainEventPublisher);
    }

    @Override
    public void approveBooking(Booking booking) {
        booking.approve();
        log.info("Booking with id: {} is approved", booking.getId().getValue());
    }

    @Override
    public BookingCancelledEvent cancelBookingPayment(Booking booking, List<String> failureMessages,
                                                      DomainEventPublisher<BookingCancelledEvent>
                                                              bookingCancelledEventDomainEventPublisher) {
        booking.initCancel(failureMessages);
        log.info("Booking payment is cancelling for booking id: {}", booking.getId().getValue());
        return new BookingCancelledEvent(booking, ZonedDateTime.now(ZoneId.of(UTC)),
                bookingCancelledEventDomainEventPublisher);
    }

    @Override
    public void cancelBooking(Booking booking, List<String> failureMessages) {
        booking.cancel(failureMessages);
        log.info("Booking with id: {} is cancelled", booking.getId().getValue());
    }

    private void validateCinema(Cinema cinema) {
        if (!cinema.isActive()) {
            throw new BookingDomainException("Cinema with id " + cinema.getId().getValue() +
                    " is currently not active!");
        }
    }

    private void setBookingMovieInformation(Booking booking, Cinema cinema) {
        booking.getItems().forEach(bookingItem -> cinema.getMovies().forEach(CinemaMovie -> {
            Movie currentMovie = bookingItem.getMovie();
            if (currentMovie.equals(CinemaMovie)) {
                currentMovie.updateWithConfirmedNameAndPrice(CinemaMovie.getName(),
                        CinemaMovie.getPrice());
            }
        }));
    }
}
