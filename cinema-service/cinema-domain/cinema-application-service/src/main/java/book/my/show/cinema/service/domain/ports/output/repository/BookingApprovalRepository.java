package book.my.show.cinema.service.domain.ports.output.repository;

import book.my.show.cinema.service.domain.entity.BookingApproval;


public interface BookingApprovalRepository {
    BookingApproval save(BookingApproval bookingApproval );
}
