package book.my.show.cinema.service.domain;


import book.my.show.booking.service.domain.event.publisher.DomainEventPublisher;
import book.my.show.booking.service.domain.valueobject.BookingApprovalStatus;
import book.my.show.cinema.service.domain.entity.Cinema;
import book.my.show.cinema.service.domain.event.BookingApprovalEvent;
import book.my.show.cinema.service.domain.event.BookingApprovedEvent;
import book.my.show.cinema.service.domain.event.BookingRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static book.my.show.booking.service.domain.constants.DomainConstants.UTC;


@Slf4j
public class CinemaDomainServiceImpl implements CinemaDomainService {

    @Override
    public BookingApprovalEvent validateBooking(Cinema cinema,
                                                List<String> failureMessages,
                                                DomainEventPublisher<BookingApprovedEvent>
                                                    bookingApprovedEventDomainEventPublisher,
                                                DomainEventPublisher<BookingRejectedEvent>
                                                    bookingRejectedEventDomainEventPublisher) {
        cinema.validateBooking(failureMessages);
        log.info("Validating booking with id: {}", cinema.getBookingDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Booking is approved for booking id: {}", cinema.getBookingDetail().getId().getValue());
            cinema.constructBookingApproval(BookingApprovalStatus.APPROVED);
            return new BookingApprovedEvent(cinema.getBookingApproval(),
                    cinema.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    bookingApprovedEventDomainEventPublisher);
        } else {
            log.info("Booking is rejected for booking id: {}", cinema.getBookingDetail().getId().getValue());
            cinema.constructBookingApproval(BookingApprovalStatus.REJECTED);
            return new BookingRejectedEvent(cinema.getBookingApproval(),
                    cinema.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    bookingRejectedEventDomainEventPublisher);
        }
    }
}
