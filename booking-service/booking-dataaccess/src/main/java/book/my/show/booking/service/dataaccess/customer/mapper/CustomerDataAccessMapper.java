package book.my.show.booking.service.dataaccess.customer.mapper;


import book.my.show.booking.service.dataaccess.customer.entity.CustomerEntity;
import book.my.show.booking.service.domain.entity.Customer;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
