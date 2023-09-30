package book.my.show.booking.service.domain.entity;


import book.my.show.booking.service.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
