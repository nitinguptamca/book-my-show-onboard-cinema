package book.my.show.cinema.mgmt.service.customer.repository;


import book.my.show.cinema.mgmt.service.customer.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, UUID> {
}
