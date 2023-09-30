package book.my.show.booking.service.domain.valueobject;

import java.util.UUID;

public class SeatId extends BaseId<UUID> {
    public SeatId(UUID value) {
        super(value);
    }
}
