package book.my.show.cinema.mgmt.service.cinema.repository;

import book.my.show.cinema.mgmt.service.cinema.Entity.CinemaOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaOwnerRepository extends JpaRepository<CinemaOwner, UUID> {
}
