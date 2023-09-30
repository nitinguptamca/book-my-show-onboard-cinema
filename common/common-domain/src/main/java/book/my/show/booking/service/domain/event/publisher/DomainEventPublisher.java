package book.my.show.booking.service.domain.event.publisher;

import book.my.show.booking.service.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}

