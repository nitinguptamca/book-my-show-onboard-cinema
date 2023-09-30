package book.my.show.booking.service.domain.event;

public interface DomainEvent<T> {
    void fire();
}
