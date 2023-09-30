package book.my.show.cinema.service.dataaccess.cinema.repository;

import book.my.show.cinema.service.dataaccess.cinema.entity.BookingApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingApprovalJpaRepository extends JpaRepository<BookingApprovalEntity, UUID> {


}
