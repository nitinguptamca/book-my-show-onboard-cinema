package book.my.show.customer.service.service.impl;

import book.my.show.customer.service.Entity.Customers;
import book.my.show.customer.service.repository.CustomersRepository;
import book.my.show.customer.service.service.CustomerService;
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
