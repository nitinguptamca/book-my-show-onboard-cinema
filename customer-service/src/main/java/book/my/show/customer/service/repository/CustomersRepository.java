package book.my.show.customer.service.repository;

import book.my.show.customer.service.Entity.Customers;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, UUID> {
}
