package book.my.show.cinema.service.domain.entity;

import book.my.show.booking.service.domain.entity.BaseEntity;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;

import java.time.LocalDateTime;

public class Movie extends BaseEntity<MovieId> {
    private String name;
    private Money price;
    private  int quantity;
    private boolean available;
    private LocalDateTime movieTime;
    private String seatNumber;

    public void updateWithConfirmedNamePriceAndAvailability(String name, Money price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    private Movie(Builder builder) {
        setId(builder.movieId);
        name = builder.name;
        price = builder.price;
        quantity = builder.quantity;
        available = builder.available;
        movieTime=builder.movieTime;
        seatNumber=builder.seatNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDateTime getMovieTime(){ return  movieTime;}

    public String getSeatNumber(){return  seatNumber;}

    public static final class Builder {
        private MovieId movieId;
        private String name;
        private Money price;
        private int quantity;
        private boolean available;
        private LocalDateTime movieTime;
        private String seatNumber;

        private Builder() {
        }

        public Builder movieId(MovieId val) {
            movieId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }
        public Builder movieTime(LocalDateTime   val) {
            movieTime = val;
            return this;
        } public Builder seatNumber(String val) {
            seatNumber = val;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
