package book.my.show.booking.service.domain.dto.track;


import book.my.show.booking.service.domain.valueobject.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackBookingResponse {
    @NotNull
    private final UUID bookingTrackingId;
    @NotNull
    private final BookingStatus bookingStatus;
    private final List<String> failureMessages;
}
