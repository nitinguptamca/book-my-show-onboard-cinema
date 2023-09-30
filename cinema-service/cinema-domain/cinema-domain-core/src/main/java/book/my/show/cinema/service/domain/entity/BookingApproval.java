package book.my.show.cinema.service.domain.entity;


import book.my.show.booking.service.domain.entity.BaseEntity;
import book.my.show.booking.service.domain.valueobject.BookingApprovalStatus;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.cinema.service.domain.valueobject.BookingApprovalId;

public class BookingApproval extends BaseEntity<BookingApprovalId> {
    private final CinemaId cinemaId;
    private final BookingId bookingId;
    private final BookingApprovalStatus bookingApprovalStatus;

    private BookingApproval(Builder builder) {
        setId(builder.bookingApprovalId);
        cinemaId = builder.cinemaId;
        bookingId = builder.bookingId;
        bookingApprovalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public CinemaId getCinemaId() {
        return cinemaId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public BookingApprovalStatus getBookingApprovalStatus() {
        return bookingApprovalStatus;
    }

    public static final class Builder {
        private BookingApprovalId bookingApprovalId;
        private CinemaId cinemaId;
        private BookingId bookingId;
        private BookingApprovalStatus approvalStatus;

        private Builder() {
        }

        public Builder bookingApprovalId(BookingApprovalId val) {
            bookingApprovalId = val;
            return this;
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }

        public Builder bookingId(BookingId val) {
            bookingId = val;
            return this;
        }

        public Builder approvalStatus(BookingApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public BookingApproval build() {
            return new BookingApproval(this);
        }
    }
}
