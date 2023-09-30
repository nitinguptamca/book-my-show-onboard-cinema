package book.my.show.booking.service.domain.dto.message;


import book.my.show.booking.service.domain.valueobject.BookingApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CinemaApprovalResponse {
    private String id;
    private String sagaId;
    private String bookingId;
    private String cinemaId;
    private Instant createdAt;
    private BookingApprovalStatus bookingApprovalStatus;
    private List<String> failureMessages;
}
