package book.my.show.payment.service.domain.ports.output.repository;


import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.payment.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
