package book.my.show.cinema.mgmt.service.customer.service.impl;


import book.my.show.cinema.mgmt.service.customer.Entity.Customers;
import book.my.show.cinema.mgmt.service.customer.repository.CustomersRepository;
import book.my.show.cinema.mgmt.service.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomersRepository customersRepository;

    public CustomerServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public Customers create(Customers customers) {
        return customersRepository.save(customers);
    }
}
