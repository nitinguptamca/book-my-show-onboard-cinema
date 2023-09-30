package book.my.show.cinema.service.domain.entity;



import book.my.show.booking.service.domain.entity.AggregateRoot;
import book.my.show.booking.service.domain.valueobject.BookingApprovalStatus;
import book.my.show.booking.service.domain.valueobject.BookingStatus;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.cinema.service.domain.valueobject.BookingApprovalId;

import java.util.List;
import java.util.UUID;

public class Cinema extends AggregateRoot<CinemaId> {
   private BookingApproval bookingApproval;
   private boolean active;
   private final BookingDetail bookingDetail;

   public void validateBooking(List<String> failureMessages) {
       if (bookingDetail.getBookingStatus() != BookingStatus.PAID) {
           failureMessages.add("Payment is not completed for booking: " + bookingDetail.getId());
       }
       Money totalAmount = bookingDetail.getMovies().stream().map(cinema -> {
           if (!cinema.isAvailable()) {
               failureMessages.add("Cinema with id: " + cinema.getId().getValue()
                       + " is not available");
           }
           return cinema.getPrice().multiply(cinema.getQuantity());
       }).reduce(Money.ZERO, Money::add);

       if (!totalAmount.equals(bookingDetail.getTotalAmount())) {
           failureMessages.add("Price total is not correct for booking: " + bookingDetail.getId());
       }
   }

   public void constructBookingApproval(BookingApprovalStatus bookingApprovalStatus) {
       this.bookingApproval = BookingApproval.builder()
               .bookingApprovalId(new BookingApprovalId(UUID.randomUUID()))
               .cinemaId(this.getId())
               .bookingId(this.getBookingDetail().getId())
               .approvalStatus(bookingApprovalStatus)
               .build();
   }

    public void setActive(boolean active) {
        this.active = active;
    }

    private Cinema(Builder builder) {
        setId(builder.cinemaId);
        bookingApproval = builder.bookingApproval;
        active = builder.active;
        bookingDetail = builder.bookingDetail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public BookingApproval getBookingApproval() {
        return bookingApproval;
    }

    public boolean isActive() {
        return active;
    }

    public BookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public static final class Builder {
        private CinemaId cinemaId;
        private BookingApproval bookingApproval;
        private boolean active;
        private BookingDetail bookingDetail;

        private Builder() {
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }

        public Builder bookingApproval(BookingApproval val) {
            bookingApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder bookingDetail(BookingDetail val) {
            bookingDetail = val;
            return this;
        }

        public Cinema build() {
            return new Cinema(this);
        }
    }
}
