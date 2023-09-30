package book.my.show.booking.service.domain.mapper;



import book.my.show.booking.service.domain.dto.create.BookingItem;
import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.dto.create.CreateBookingResponse;
import book.my.show.booking.service.domain.dto.track.TrackBookingResponse;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.entity.Cinema;
import book.my.show.booking.service.domain.entity.Movie;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingDataMapper {

    public Cinema createBookingCommandToCinema(CreateBookingCommand createBookingCommand) {
        return Cinema.builder()
                .cinemaId(new CinemaId(createBookingCommand.getCinemaId()))
                .movies(createBookingCommand.getItems().stream().map(bookingItem ->
                        new Movie(new MovieId(bookingItem.getMovieId())))
                        .collect(Collectors.toList()))
                .build();
    }
    
    public Booking createBookingCommandToBooking(CreateBookingCommand createBookingCommand) {
        return Booking.builder()
                .customerId(new CustomerId(createBookingCommand.getCustomerId()))
                .cinemaId(new CinemaId(createBookingCommand.getCinemaId()))
                .price(new Money(createBookingCommand.getPrice()))
                .items(bookingItemsToBookingItemEntities(createBookingCommand.getItems()))
                .build();
    }

    public CreateBookingResponse bookingToCreateBookingResponse(Booking booking, String message) {
        return CreateBookingResponse.builder()
                .bookingTrackingId(booking.getTrackingId().getValue())
                .bookingStatus(booking.getBookingStatus())
                .message(message)
                .build();
    }

    public TrackBookingResponse bookingToTrackBookingResponse(Booking booking) {
        return TrackBookingResponse.builder()
                .bookingTrackingId(booking.getTrackingId().getValue())
                .bookingStatus(booking.getBookingStatus())
                .failureMessages(booking.getFailureMessages())
                .build();
    }

    private List<book.my.show.booking.service.domain.entity.BookingItem> bookingItemsToBookingItemEntities(
            List<book.my.show.booking.service.domain.dto.create.BookingItem> bookingItems) {
        return bookingItems.stream()
                .map(bookingItem ->
                        book.my.show.booking.service.domain.entity.BookingItem.builder()
                                .movie(new Movie(new MovieId(bookingItem.getMovieId())))
                                .seatNumber(bookingItem.getSeatNumber())
                                .movieTime(bookingItem.getMovieTime())
                                .price(bookingItem.getPrice())
                                .quantity(bookingItem.getQuantity())
                                .subTotal(new Money(bookingItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

}
