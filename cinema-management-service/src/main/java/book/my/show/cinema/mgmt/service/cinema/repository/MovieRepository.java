package book.my.show.cinema.mgmt.service.cinema.repository;

import book.my.show.cinema.mgmt.service.cinema.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
