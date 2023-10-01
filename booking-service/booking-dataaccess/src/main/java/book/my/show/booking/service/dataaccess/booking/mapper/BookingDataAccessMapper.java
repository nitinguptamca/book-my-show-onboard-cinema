package book.my.show.booking.service.dataaccess.booking.mapper;


import book.my.show.booking.service.dataaccess.booking.entity.BookingEntity;
import book.my.show.booking.service.dataaccess.booking.entity.BookingItemEntity;

import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.entity.BookingItem;
import book.my.show.booking.service.domain.entity.Movie;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.BookingItemId;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.MovieId;
import book.my.show.booking.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static book.my.show.booking.service.domain.constants.DomainConstants.FAILURE_MESSAGE_DELIMITER;


@Component
public class BookingDataAccessMapper {

    public BookingEntity bookingTobookingEntity(Booking booking) {
        BookingEntity bookingEntity = BookingEntity.builder()
                .id(booking.getId().getValue())
                .customerId(booking.getCustomerId().getValue())
                .cinemaId(booking.getcinemaId().getValue())
                .trackingId(booking.getTrackingId().getValue())
                .price(booking.getPrice().getAmount())
                .items(bookingItemsToBookingItemEntities(booking.getItems()))
                .bookingStatus(booking.getBookingStatus())
                .failureMessages(booking.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, booking.getFailureMessages()) : "")
                .build();
        bookingEntity.getItems()
                .forEach(bookingItemEntity -> bookingItemEntity
                        .setBooking(bookingEntity));

        return bookingEntity;
    }

    public Booking bookingEntityToBooking(BookingEntity bookingEntity) {
        return Booking.builder()
                .bookingId(new BookingId(bookingEntity.getId()))
                .customerId(new CustomerId(bookingEntity.getCustomerId()))
                .cinemaId(new CinemaId(bookingEntity.getCinemaId()))
                .price(new Money(bookingEntity.getPrice()))
                .items(bookingItemEntitiesToBookingItems(bookingEntity.getItems()))
                .trackingId(new TrackingId(bookingEntity.getTrackingId()))
                .bookingStatus(bookingEntity.getBookingStatus())
                .failureMessages(bookingEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(bookingEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }

    private List<BookingItem> bookingItemEntitiesToBookingItems(List<BookingItemEntity> items) {
        return items.stream().map(
              bookingItemEntity -> BookingItem.builder()
                      .BookingItemId(new BookingItemId(bookingItemEntity.getId()))
                      .movie(new Movie(new MovieId(bookingItemEntity.getMovieId())))
                      .price(new Money(bookingItemEntity.getPrice()))
                      .quantity(bookingItemEntity.getQuantity())
                      .seatNumber(bookingItemEntity.getSeatNumber())
                      .movieTime(bookingItemEntity.getMovieTime())
                      .subTotal(new Money(bookingItemEntity.getSubTotal()))
                      .build()
        ).collect(Collectors.toList());

    }


    private List<BookingItemEntity> bookingItemsToBookingItemEntities(List<BookingItem> items) {
        return items.stream()
                .map(bookingItem -> BookingItemEntity.builder()
                        .id(bookingItem.getId().getValue())
                        .movieId(bookingItem.getMovie().getId().getValue())
                        .price(bookingItem.getPrice().getAmount())
                        .quantity(bookingItem.getQuantity())
                        .seatNumber(bookingItem.getSeatNumber())
                        .movieTime(bookingItem.getMovieTime())
                        .subTotal(bookingItem.getSubTotal().getAmount())
                        .build())
                .collect(Collectors.toList());
    }

}
