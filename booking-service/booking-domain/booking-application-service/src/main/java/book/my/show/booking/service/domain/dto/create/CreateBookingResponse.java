package book.my.show.booking.service.domain.dto.create;


import book.my.show.booking.service.domain.valueobject.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateBookingResponse {
    @NotNull
    private final UUID bookingTrackingId;
    @NotNull
    private final BookingStatus bookingStatus;
    @NotNull
    private final String message;

}
