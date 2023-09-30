package book.my.show.booking.service.domain.dto.create;

import book.my.show.booking.service.domain.valueobject.Money;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class BookingItem {
    @NotNull
    private final UUID movieId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final Money price;
    @NotNull
    private final BigDecimal subTotal;
    @NotNull
    private final String seatNumber;
    @NotNull
    private final Instant movieTime;
}
