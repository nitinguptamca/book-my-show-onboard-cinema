package book.my.show.booking.service.domain.entity;


import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.BookingItemId;

import java.time.Instant;

public class BookingItems extends BaseEntity<BookingItemId> {
    private BookingId bookingId;
    private final Movie movie;
    private final int quantity;
    private final Money price;
    private final Money subTotal;
    private final String seatNumber;
    private final Instant movieTime;

    void initializeBookingItem(BookingId bookingId , BookingItemId bookingItemId) {
        this.bookingId = bookingId;
        super.setId(bookingItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(movie.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    private BookingItems(Builder builder) {
        super.setId(builder.bookingItemId);
        movie = builder.movie;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        movieTime=builder.movieTime;
        seatNumber=builder.seatNumber;

    }

    public static Builder builder() {
        return new Builder();
    }


    public BookingId getBookingId() {
        return bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public Instant getMovieTime(){return movieTime;}

    public String getSeatNumber(){return seatNumber;}



    public static final class Builder {
        private BookingItemId bookingItemId;
        private Movie movie;
        private int quantity;
        private Money price;
        private Money subTotal;
        private Instant movieTime;
        private String seatNumber;

        private Builder() {
        }

        public Builder BookingItemId(BookingItemId val) {
            bookingItemId = val;
            return this;
        }

        public Builder movie(Movie val) {
            movie = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }
        public Builder movieTime(Instant val) {
            movieTime = val;
            return this;
        }
        public Builder seatNumber(String val) {
            seatNumber = val;
            return this;
        }

        public BookingItems build() {
            return new BookingItems(this);
        }
    }
}
