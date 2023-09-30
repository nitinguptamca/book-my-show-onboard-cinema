package book.my.show.cinema.service.dataaccess.cinema.mapper;


import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;
import book.my.show.cinema.service.domain.entity.BookingApproval;
import book.my.show.cinema.service.domain.entity.BookingDetail;
import book.my.show.cinema.service.domain.entity.Cinema;
import book.my.show.cinema.service.domain.entity.Movie;
import book.my.show.cinema.service.domain.valueobject.BookingApprovalId;
import book.my.show.dataaccess.cinema.entiry.CinemaEntity;
import book.my.show.dataaccess.cinema.exception.CinemaDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataAccessMapper {

    public List<UUID> cinemaToCinemaMovies(Cinema cinema) {
        return cinema.getBookingDetail().getMovies().stream()
                .map(movie -> movie.getId().getValue())
                .collect(Collectors.toList());
    }

    public Cinema cinemaEntityToCinema(List<CinemaEntity> cinemaEntities) {
        CinemaEntity cinemaEntity =
                cinemaEntities.stream().findFirst().orElseThrow(() ->
                        new CinemaDataAccessException("No cinemas found!"));

        List<Movie> cinemaMovies = cinemaEntities.stream().map(entity ->
                        Movie.builder()
                                .movieId(new MovieId(entity.getMovieId()))
                                .name(entity.getMovieName())
                                .price(new Money(entity.getMoviePrice()))
                                .seatNumber(entity.getSeatNumber())
                                .movieTime(entity.getMovieTime())
                                .available(entity.getMovieAvailable())
                                .build())
                .collect(Collectors.toList());

        return Cinema.builder()
                .cinemaId(new CinemaId(cinemaEntity.getCinemaId()))
                .bookingDetail(BookingDetail.builder()
                        .movies(cinemaMovies)
                        .build())
                .active(cinemaEntity.getCinemaActive())
                .build();
    }

    public book.my.show.cinema.service.dataaccess.cinema.entity.BookingApprovalEntity bookingApprovalToBookingApprovalEntity(BookingApproval bookingApproval) {
        return book.my.show.cinema.service.dataaccess.cinema.entity.BookingApprovalEntity.builder()
                .id(bookingApproval.getId().getValue())
                .cinemaId(bookingApproval.getCinemaId().getValue())
                .bookingId(bookingApproval.getBookingId().getValue())
                .bookingApprovalStatus(bookingApproval.getBookingApprovalStatus())
                .build();
    }

    public BookingApproval bookingApprovalEntityToBookingApproval(book.my.show.cinema.service.dataaccess.cinema.entity.BookingApprovalEntity bookingApprovalEntity) {
        return BookingApproval.builder()
                .bookingApprovalId(new BookingApprovalId(bookingApprovalEntity.getId()))
                .cinemaId(new CinemaId(bookingApprovalEntity.getCinemaId()))
                .bookingId(new BookingId(bookingApprovalEntity.getBookingId()))
                .approvalStatus(bookingApprovalEntity.getBookingApprovalStatus())
                .build();
    }

}
