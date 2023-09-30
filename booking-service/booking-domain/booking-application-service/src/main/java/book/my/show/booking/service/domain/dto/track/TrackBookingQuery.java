package book.my.show.booking.service.domain.dto.track;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackBookingQuery {
    @NotNull
    private final UUID bookingTrackingId;
}
