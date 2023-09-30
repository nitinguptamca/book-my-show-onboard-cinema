package book.my.show.payment.service.domain.ports.output.repository;

import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
