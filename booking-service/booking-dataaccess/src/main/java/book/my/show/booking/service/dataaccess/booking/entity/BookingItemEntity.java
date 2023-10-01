package book.my.show.booking.service.dataaccess.booking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BookingItemEntityId.class)
@Table(name = "booking_items")
@Entity
public class BookingItemEntity {
    @Id
    private Long id;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOKING_ID")
    private BookingEntity booking;

    private UUID movieId;
    private BigDecimal price;
    private Integer quantity;
    private String seatNumber;
    private Instant movieTime;
    private BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingItemEntity that = (BookingItemEntity) o;
        return id.equals(that.id) && booking.equals(that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booking);
    }
}
