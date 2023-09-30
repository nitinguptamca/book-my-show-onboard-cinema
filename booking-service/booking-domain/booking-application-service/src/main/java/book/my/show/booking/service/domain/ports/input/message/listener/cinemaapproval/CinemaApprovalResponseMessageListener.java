package book.my.show.booking.service.domain.ports.input.message.listener.cinemaapproval;

import book.my.show.booking.service.domain.dto.message.CinemaApprovalResponse;

public interface CinemaApprovalResponseMessageListener {

    void bookingApproved(CinemaApprovalResponse cinemaApprovalResponse);

    void bookingRejected(CinemaApprovalResponse cinemaApprovalResponse);
}
