package book.my.show.dataaccess.cinema.repository;

import book.my.show.dataaccess.cinema.entiry.CinemaEntity;
import book.my.show.dataaccess.cinema.entiry.CinemaEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CinemaJpaRepository extends JpaRepository<CinemaEntity , CinemaEntityId> {
    Optional<List<CinemaEntity>> findByCinemaIdAndMovieIdIn(UUID cinemaId, List<UUID> movieId );

}
