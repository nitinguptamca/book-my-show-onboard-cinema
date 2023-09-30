package book.my.show.cinema.service.dataaccess.cinema.entity;

import book.my.show.booking.service.domain.valueobject.BookingApprovalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_approval", schema = "cinema")
@Entity
public class BookingApprovalEntity {

    @Id
    private UUID id;
    private UUID cinemaId;
    private UUID bookingId;
    @Enumerated(EnumType.STRING)
    private BookingApprovalStatus bookingApprovalStatus ;
}
