package book.my.show.cinema.service.domain.ports.output.repository;

import book.my.show.cinema.service.domain.entity.Cinema;

import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findCinemaInformation(Cinema cinema);
}
