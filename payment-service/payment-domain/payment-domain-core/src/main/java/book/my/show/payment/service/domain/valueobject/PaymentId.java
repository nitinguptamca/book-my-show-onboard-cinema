package book.my.show.payment.service.domain.valueobject;



import book.my.show.booking.service.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
