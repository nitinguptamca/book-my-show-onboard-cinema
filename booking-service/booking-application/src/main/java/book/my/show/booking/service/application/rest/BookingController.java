package book.my.show.booking.service.application.rest;


import book.my.show.booking.service.domain.dto.create.CreateBookingCommand;
import book.my.show.booking.service.domain.dto.create.CreateBookingResponse;
import book.my.show.booking.service.domain.dto.track.TrackBookingQuery;
import book.my.show.booking.service.domain.dto.track.TrackBookingResponse;
import book.my.show.booking.service.domain.ports.input.service.BookingApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/bookings", produces = "application/vnd.api.v1+json")
public class BookingController {

    private final BookingApplicationService bookingApplicationService;

    public BookingController(BookingApplicationService bookingApplicationService) {
        this.bookingApplicationService = bookingApplicationService;
    }


    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody CreateBookingCommand createBookingCommand) {
        log.info("Creating booking for customer: {} at cinema: {}", createBookingCommand.getCustomerId(),
                createBookingCommand.getCinemaId());
        CreateBookingResponse createBookingResponse = bookingApplicationService.createBooking(createBookingCommand);
        log.info("Booking created with tracking id: {}", createBookingResponse.getBookingTrackingId());
        return ResponseEntity.ok(createBookingResponse);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackBookingResponse> getBookingByTrackingId(@PathVariable UUID trackingId) {
       TrackBookingResponse trackBookingResponse =
               bookingApplicationService.trackBooking(TrackBookingQuery.builder().bookingTrackingId(trackingId).build());
       log.info("Returning booking status with tracking id: {}", trackBookingResponse.getBookingTrackingId());
       return  ResponseEntity.ok(trackBookingResponse);
    }
}
