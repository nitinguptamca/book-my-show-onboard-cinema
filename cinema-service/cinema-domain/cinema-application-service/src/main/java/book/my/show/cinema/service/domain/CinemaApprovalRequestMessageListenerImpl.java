package book.my.show.cinema.service.domain;


import book.my.show.cinema.service.domain.dto.CinemaApprovalRequest;
import book.my.show.cinema.service.domain.event.BookingApprovalEvent;
import book.my.show.cinema.service.domain.ports.input.message.listener.CinemaApprovalRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CinemaApprovalRequestMessageListenerImpl implements CinemaApprovalRequestMessageListener {

    private final CinemaApprovalRequestHelper cinemaApprovalRequestHelper;

    public CinemaApprovalRequestMessageListenerImpl(CinemaApprovalRequestHelper
                                                            cinemaApprovalRequestHelper) {
        this.cinemaApprovalRequestHelper = cinemaApprovalRequestHelper;
    }

    @Override
    public void approveBooking(CinemaApprovalRequest cinemaApprovalRequest) {
        BookingApprovalEvent bookingApprovalEvent =
                cinemaApprovalRequestHelper.persistOrderApproval(cinemaApprovalRequest);
        bookingApprovalEvent.fire();
    }
}
