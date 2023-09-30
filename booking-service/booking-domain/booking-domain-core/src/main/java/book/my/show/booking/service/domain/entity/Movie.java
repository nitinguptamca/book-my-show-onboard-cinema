package book.my.show.booking.service.domain.entity;

import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;

import java.time.Instant;


public class Movie extends BaseEntity<MovieId> {
    private String name;
    private String seatNumber;
    private Instant movietime;
    private Money price;

    public Movie(MovieId movieId, String name, String seatNumber, Instant movietime, Money price) {
        super.setId(movieId);
        this.name = name;
        this.seatNumber = seatNumber;
        this.movietime = movietime;
        this.price = price;
    }

    public Movie(MovieId movieId, String name, Money price) {
        super.setId(movieId);
        this.name = name;
        this.price = price;
    }



    public Movie(MovieId movieId ) {
        super.setId(movieId);
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Instant getMovietime() {
        return movietime;
    }
}
