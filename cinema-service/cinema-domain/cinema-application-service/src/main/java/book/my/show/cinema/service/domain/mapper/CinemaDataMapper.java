package book.my.show.cinema.service.domain.mapper;

import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.BookingStatus;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.cinema.service.domain.dto.CinemaApprovalRequest;

import book.my.show.cinema.service.domain.entity.BookingDetail;
import book.my.show.cinema.service.domain.entity.Cinema;
import book.my.show.cinema.service.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataMapper {
    public Cinema cinemaApprovalRequestTocinema(CinemaApprovalRequest
                                                                    cinemaApprovalRequest) {
        return Cinema.builder()
                .cinemaId(new CinemaId(UUID.fromString(cinemaApprovalRequest.getCinemaId())))
                .bookingDetail(BookingDetail.builder()
                        .bookingId(new BookingId(UUID.fromString(cinemaApprovalRequest.getBookingId())))
                        .movies(cinemaApprovalRequest.getMovies().stream().map(
                                        movie -> Movie.builder()
                                                .movieId(movie.getId())
                                                .quantity(movie.getQuantity())
                                                .available(movie.isAvailable())
                                                .movieTime(movie.getMovieTime())
                                                .seatNumber(movie.getSeatNumber())
                                                .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(cinemaApprovalRequest.getPrice()))
                        .bookingStatus(BookingStatus.valueOf(cinemaApprovalRequest.getCinemaBookingStatus().name()))
                        .build())
                .build();
    }
}
