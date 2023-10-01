package book.my.show.cinema.mgmt.service.cinema.repository;

import book.my.show.cinema.mgmt.service.cinema.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, UUID> {
}
