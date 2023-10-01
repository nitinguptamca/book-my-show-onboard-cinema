package book.my.show.booking.service.dataaccess.booking.repository;

import book.my.show.booking.service.dataaccess.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {

    Optional<BookingEntity> findByTrackingId(UUID trackingId);
}
