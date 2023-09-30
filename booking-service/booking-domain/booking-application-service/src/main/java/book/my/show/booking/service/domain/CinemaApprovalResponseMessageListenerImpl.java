package book.my.show.booking.service.domain;


import book.my.show.booking.service.domain.dto.message.CinemaApprovalResponse;
import book.my.show.booking.service.domain.event.BookingCancelledEvent;
import book.my.show.booking.service.domain.ports.input.message.listener.cinemaapproval.CinemaApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static book.my.show.booking.service.domain.constants.DomainConstants.FAILURE_MESSAGE_DELIMITER;


@Slf4j
@Validated
@Service
public class CinemaApprovalResponseMessageListenerImpl implements CinemaApprovalResponseMessageListener {

    private final BookingApprovalSaga bookingApprovalSaga;

    public CinemaApprovalResponseMessageListenerImpl(BookingApprovalSaga bookingApprovalSaga) {
        this.bookingApprovalSaga = bookingApprovalSaga;
    }

    @Override
    public void bookingApproved(CinemaApprovalResponse cinemaApprovalResponse) {
        bookingApprovalSaga.process(cinemaApprovalResponse);
        log.info("Booking is approved for booking id: {}", cinemaApprovalResponse.getBookingId());
    }

    @Override
    public void bookingRejected(CinemaApprovalResponse cinemaApprovalResponse) {
          BookingCancelledEvent domainEvent = bookingApprovalSaga.rollback(cinemaApprovalResponse);
          log.info("Publishing booking cancelled event for booking id: {} with failure messages: {}",
                  cinemaApprovalResponse.getBookingId(),
                  String.join(FAILURE_MESSAGE_DELIMITER, cinemaApprovalResponse.getFailureMessages()));
          domainEvent.fire();
    }
}
