package book.my.show.booking.service.domain.ports.output.repository;



import book.my.show.booking.service.domain.entity.Cinema;

import java.util.Optional;

public interface CinemaRepository {

    Optional<Cinema> findCinemaInformation(Cinema cinema);
}
