package book.my.show.cinema.service.domain.dto;


import book.my.show.booking.service.domain.valueobject.CinemaBookingStatus;

import book.my.show.cinema.service.domain.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CinemaApprovalRequest {
    private String id;
    private String sagaId;
    private String cinemaId;
    private String bookingId;
    private CinemaBookingStatus cinemaBookingStatus;
    private java.util.List<Movie> movies;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
}
