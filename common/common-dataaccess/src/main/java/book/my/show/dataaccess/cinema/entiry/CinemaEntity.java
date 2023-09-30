package book.my.show.dataaccess.cinema.entiry;


import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CinemaEntityId.class)
@Table(name = "booking_cinema_m_view" , schema = "cinema")
public class CinemaEntity {
    @Id
    private UUID cinemaId;
    @Id
    private UUID movieId;

    private String cinemaName;
    private Boolean cinemaActive;
    private String MovieName;
    private BigDecimal moviePrice;
    private Boolean movieAvailable;
    private Instant movieTime;
    private String seatNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaEntity that = (CinemaEntity) o;
        return Objects.equals(cinemaId, that.cinemaId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaId, movieId);
    }

    @Override
    public String toString() {
        return "CinemaEntity{" +
                "cinemaId=" + cinemaId +
                ", movieId=" + movieId +
                '}';
    }
}
