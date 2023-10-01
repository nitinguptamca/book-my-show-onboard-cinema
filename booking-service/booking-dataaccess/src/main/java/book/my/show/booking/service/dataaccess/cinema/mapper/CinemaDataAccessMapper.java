package book.my.show.booking.service.dataaccess.cinema.mapper;


import book.my.show.booking.service.domain.entity.Cinema;
import book.my.show.booking.service.domain.entity.Movie;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;
import book.my.show.dataaccess.cinema.entiry.CinemaEntity;
import book.my.show.dataaccess.cinema.exception.CinemaDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataAccessMapper {

    public List<UUID> cinemaToCinemaMovies(Cinema cinema) {
        return cinema.getMovies().stream()
                .map(movie -> movie.getId().getValue())
                .collect(Collectors.toList());
    }

    public Cinema cinemaEntityToCinema(List<CinemaEntity> cinemaEntities) {
        CinemaEntity cinemaEntity =
                cinemaEntities.stream().findFirst().orElseThrow(() ->
                        new CinemaDataAccessException("Cinema could not be found!"));

        List<Movie> cinemamovies = cinemaEntities.stream().map(entity ->
                new Movie(new MovieId(entity.getMovieId()), entity.getMovieName(),
                        entity.getSeatNumber(),entity.getMovieTime(),
                        new Money(entity.getMoviePrice()))).toList();

        return Cinema.builder()
                .cinemaId(new CinemaId(cinemaEntity.getCinemaId()))
                .movies(cinemamovies)
                .active(cinemaEntity.getCinemaActive())
                .build();
    }
}
