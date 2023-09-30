package book.my.show.booking.service.domain.ports.output.repository;


import book.my.show.booking.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
}
