package book.my.show.cinema.service.domain.valueobject;


import book.my.show.booking.service.domain.valueobject.BaseId;

import java.util.UUID;

public class BookingApprovalId extends BaseId<UUID> {
    public BookingApprovalId(UUID value) {
        super(value);
    }
}
