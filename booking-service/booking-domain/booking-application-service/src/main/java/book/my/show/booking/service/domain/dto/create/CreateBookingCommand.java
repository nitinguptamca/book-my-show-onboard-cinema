package book.my.show.booking.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateBookingCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final UUID cinemaId;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final List<BookingItem> items;
}
