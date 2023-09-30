package book.my.show.cinema.service.domain.ports.input.message.listener;


import book.my.show.cinema.service.domain.dto.CinemaApprovalRequest;

public interface CinemaApprovalRequestMessageListener {
    void approveBooking(CinemaApprovalRequest cinemaApprovalRequest);
}
