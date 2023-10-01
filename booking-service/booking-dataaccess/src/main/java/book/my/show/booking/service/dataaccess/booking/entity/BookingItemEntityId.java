package book.my.show.booking.service.dataaccess.booking.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemEntityId implements Serializable {

    private Long id;
    private BookingEntity booking;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingItemEntityId that = (BookingItemEntityId) o;
        return id.equals(that.id) && booking.equals(that.booking);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, booking);
    }
}
