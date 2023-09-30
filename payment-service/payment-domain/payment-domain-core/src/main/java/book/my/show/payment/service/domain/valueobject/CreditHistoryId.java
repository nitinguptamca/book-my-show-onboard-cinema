package book.my.show.payment.service.domain.valueobject;


import book.my.show.booking.service.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {
    public CreditHistoryId(UUID value) {
        super(value);
    }
}
