package book.my.show.cinema.service.messaging.mapper;


import book.my.show.booking.service.domain.valueobject.CinemaBookingStatus;
import book.my.show.booking.service.domain.valueobject.MovieId;
import book.my.show.cinema.service.domain.dto.CinemaApprovalRequest;
import book.my.show.cinema.service.domain.entity.Movie;
import book.my.show.cinema.service.domain.event.BookingApprovedEvent;
import book.my.show.cinema.service.domain.event.BookingRejectedEvent;
import book.my.show.kafka.booking.avro.model.BookingApprovalStatus;
import book.my.show.kafka.booking.avro.model.CinemaApprovalRequestAvroModel;
import book.my.show.kafka.booking.avro.model.CinemaApprovalResponseAvroModel;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaMessagingDataMapper {
    public CinemaApprovalResponseAvroModel
    bookingApprovedEventToCinemaApprovalResponseAvroModel(BookingApprovedEvent bookingApprovedEvent) {
        return CinemaApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setBookingId(bookingApprovedEvent.getBookingApproval().getBookingId().getValue().toString())
                .setCinemaId(bookingApprovedEvent.getCinemaId().getValue().toString())
                .setCreatedAt(bookingApprovedEvent.getCreatedAt().toInstant())
                .setBookingApprovalStatus(BookingApprovalStatus.valueOf(bookingApprovedEvent.
                        getBookingApproval().getBookingApprovalStatus().name()))
                .setFailureMessages(bookingApprovedEvent.getFailureMessages())
                .build();
    }

    public CinemaApprovalResponseAvroModel
    bookingRejectedEventToCinemaApprovalResponseAvroModel(BookingRejectedEvent bookingRejectedEvent) {
        return CinemaApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setBookingId(bookingRejectedEvent.getBookingApproval().getBookingId().getValue().toString())
                .setCinemaId(bookingRejectedEvent.getCinemaId().getValue().toString())
                .setCreatedAt(bookingRejectedEvent.getCreatedAt().toInstant())
                .setBookingApprovalStatus(BookingApprovalStatus.valueOf(bookingRejectedEvent.
                        getBookingApproval().getBookingApprovalStatus().name()))
                .setFailureMessages(bookingRejectedEvent.getFailureMessages())
                .build();
    }

    public CinemaApprovalRequest
    cinemaApprovalRequestAvroModelToCinemaApproval(CinemaApprovalRequestAvroModel
                                                                   cinemaApprovalRequestAvroModel) {
        return CinemaApprovalRequest.builder()
                .id(cinemaApprovalRequestAvroModel.getId())
                .sagaId(cinemaApprovalRequestAvroModel.getSagaId())
                .cinemaId(cinemaApprovalRequestAvroModel.getCinemaId())
                .bookingId(cinemaApprovalRequestAvroModel.getBookingId())
                .cinemaBookingStatus(CinemaBookingStatus.valueOf(cinemaApprovalRequestAvroModel
                        .getCinemaBookingStatus().name()))
                .movies(cinemaApprovalRequestAvroModel.getMovies()
                        .stream().map(avroModel ->
                                Movie.builder()
                                        .movieId(new MovieId(UUID.fromString(avroModel.getId())))
                                        .quantity(avroModel.getQuantity())
                                        .seatNumber(avroModel.getSeatNumber())
                                        .movieTime(avroModel.getMovieTime())
                                        .build())
                        .collect(Collectors.toList()))
                .price(cinemaApprovalRequestAvroModel.getPrice())
                .createdAt(cinemaApprovalRequestAvroModel.getCreatedAt())
                .build();
    }
}
