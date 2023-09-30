package book.my.show.booking.service.domain;



import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.entity.Cinema;
import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;

import java.util.List;

public interface BookingDomainService {

    BookingCreatedEvent validateAndInitiateBooking(Booking booking, Cinema cinema, DomainEventPublisher<BookingCreatedEvent> bookingCreatedEventDomainEventPublisher);

    BookingPaidEvent payBooking(Booking booking, DomainEventPublisher<BookingPaidEvent> bookingPaidEventDomainEventPublisher);

    void approveBooking(Booking booking);

    BookingCancelledEvent cancelBookingPayment(Booking booking, List<String> failureMessages, DomainEventPublisher<BookingCancelledEvent> bookingCancelledEventDomainEventPublisher);

    void cancelBooking(Booking booking, List<String> failureMessages);
}
