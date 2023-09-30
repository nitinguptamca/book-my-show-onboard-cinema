package book.my.show.payment.service.domain.valueobject;



import book.my.show.booking.service.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
