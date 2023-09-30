package book.my.show.cinema.service.domain.entity;



import book.my.show.booking.service.domain.entity.BaseEntity;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.BookingStatus;
import book.my.show.booking.service.domain.valueobject.Money;

import java.util.List;

public class BookingDetail extends BaseEntity<BookingId> {
    private BookingStatus bookingStatus;
    private Money totalAmount;
    private final List<Movie> movies;

    private BookingDetail(Builder builder) {
        setId(builder.bookingId);
        bookingStatus = builder.bookingStatus;
        totalAmount = builder.totalAmount;
        movies = builder.movies;
    }

    public static Builder builder() {
        return new Builder();
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public List<Movie> getCinemas() {
        return movies;
    }

    public static final class Builder {
        private BookingId bookingId;
        private BookingStatus bookingStatus;
        private Money totalAmount;
        private List<Movie> movies;

        private Builder() {
        }

        public Builder bookingId(BookingId val) {
            bookingId = val;
            return this;
        }

        public Builder bookingStatus(BookingStatus val) {
            bookingStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder cinemas(List<Movie> val) {
            movies = val;
            return this;
        }

        public BookingDetail build() {
            return new BookingDetail(this);
        }
    }
}
