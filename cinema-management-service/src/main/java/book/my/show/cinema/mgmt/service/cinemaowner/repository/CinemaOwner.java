package book.my.show.cinema.mgmt.service.cinemaowner.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaOwner extends JpaRepository<CinemaOwner, UUID> {
}
