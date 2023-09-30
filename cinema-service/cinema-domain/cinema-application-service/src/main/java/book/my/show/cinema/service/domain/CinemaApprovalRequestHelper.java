package book.my.show.cinema.service.domain;


import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.cinema.service.domain.dto.CinemaApprovalRequest;
import book.my.show.cinema.service.domain.entity.Cinema;
import book.my.show.cinema.service.domain.event.BookingApprovalEvent;
import book.my.show.cinema.service.domain.exception.CinemaNotFoundException;
import book.my.show.cinema.service.domain.mapper.CinemaDataMapper;
import book.my.show.cinema.service.domain.ports.output.message.publisher.BookingApprovedMessagePublisher;
import book.my.show.cinema.service.domain.ports.output.message.publisher.BookingRejectedMessagePublisher;
import book.my.show.cinema.service.domain.ports.output.repository.BookingApprovalRepository;
import book.my.show.cinema.service.domain.ports.output.repository.CinemaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class CinemaApprovalRequestHelper {

    private final CinemaDomainService cinemaDomainService;
    private final CinemaDataMapper cinemaDataMapper;
    private final CinemaRepository cinemaRepository;
    private final BookingApprovalRepository bookingApprovalRepository;
    private final BookingApprovedMessagePublisher bookingApprovedMessagePublisher;
    private final BookingRejectedMessagePublisher bookingRejectedMessagePublisher;

    public CinemaApprovalRequestHelper(CinemaDomainService cinemaDomainService,
                                       CinemaDataMapper cinemaDataMapper,
                                       CinemaRepository cinemaRepository,
                                       BookingApprovalRepository bookingApprovalRepository,
                                       BookingApprovedMessagePublisher bookingApprovedMessagePublisher,
                                       BookingRejectedMessagePublisher bookingRejectedMessagePublisher) {
        this.cinemaDomainService = cinemaDomainService;
        this.cinemaDataMapper = cinemaDataMapper;
        this.cinemaRepository = cinemaRepository;
        this.bookingApprovalRepository = bookingApprovalRepository;
        this.bookingApprovedMessagePublisher = bookingApprovedMessagePublisher;
        this.bookingRejectedMessagePublisher = bookingRejectedMessagePublisher;
    }


    @Transactional
    public BookingApprovalEvent persistBookingApproval(CinemaApprovalRequest cinemaApprovalRequest) {
        log.info("Processing cinema approval for order id: {}", cinemaApprovalRequest.getBookingId());
        List<String> failureMessages = new ArrayList<>();
        Cinema cinema = findCinema(cinemaApprovalRequest);
        BookingApprovalEvent bookingApprovalEvent=
                cinemaDomainService.validateBooking(
                        cinema,
                        failureMessages,
                        bookingApprovedMessagePublisher,
                        bookingRejectedMessagePublisher);
        bookingApprovalRepository.save(cinema.getBookingApproval());
        return bookingApprovalEvent;
    }

    private Cinema findCinema(CinemaApprovalRequest cinemaApprovalRequest) {
        Cinema cinema = cinemaDataMapper
                .cinemaApprovalRequestTocinema(cinemaApprovalRequest);
        Optional<Cinema> cinemaResult = cinemaRepository.findCinemaInformation(cinema);
        if (cinemaResult.isEmpty()) {
            log.error("cinema with id " + cinema.getId().getValue() + " not found!");
            throw new CinemaNotFoundException("cinema with id " + cinema.getId().getValue() +
                    " not found!");
        }

        Cinema cinemaEntity = cinemaResult.get();
        cinema.setActive(cinemaEntity.isActive());
        cinema.getBookingDetail().getMovies().forEach(movie ->
                cinemaEntity.getBookingDetail().getMovies().forEach(p -> {
                    if (p.getId().equals(movie.getId())) {
                        movie.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
                    }
                }));
        cinema.getBookingDetail().setId(new BookingId(UUID.fromString(cinemaApprovalRequest.getBookingId())));

        return cinema;
    }
}
