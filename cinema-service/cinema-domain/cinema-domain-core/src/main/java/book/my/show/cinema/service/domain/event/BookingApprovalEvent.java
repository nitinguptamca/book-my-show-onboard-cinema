package book.my.show.cinema.service.domain.event;

import book.my.show.booking.service.domain.event.DomainEvent;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.cinema.service.domain.entity.BookingApproval;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class BookingApprovalEvent implements DomainEvent<BookingApproval> {
    private final BookingApproval bookingApproval;
    private final CinemaId cinemaId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public BookingApprovalEvent(BookingApproval bookingApproval,
                              CinemaId cinemaId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt) {
        this.bookingApproval = bookingApproval;
        this.cinemaId = cinemaId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

    public BookingApproval getBookingApproval() {
        return bookingApproval;
    }

    public CinemaId getCinemaId() {
        return cinemaId;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
