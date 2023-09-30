package book.my.show.cinema.service.dataaccess.cinema.adapter;

import book.my.show.cinema.service.dataaccess.cinema.mapper.CinemaDataAccessMapper;
import book.my.show.cinema.service.dataaccess.cinema.repository.BookingApprovalJpaRepository;

import book.my.show.cinema.service.domain.entity.BookingApproval;
import book.my.show.cinema.service.domain.ports.output.repository.BookingApprovalRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingApprovalRepositoryImpl implements BookingApprovalRepository {

    private final BookingApprovalJpaRepository bookingApprovalJpaRepository;
    private final CinemaDataAccessMapper cinemaDataAccessMapper;

    public BookingApprovalRepositoryImpl(BookingApprovalJpaRepository bookingApprovalJpaRepository,
                                         CinemaDataAccessMapper cinemaDataAccessMapper) {
        this.bookingApprovalJpaRepository = bookingApprovalJpaRepository;
        this.cinemaDataAccessMapper = cinemaDataAccessMapper;
    }

    @Override
    public BookingApproval save(BookingApproval bookingApproval) {
        return cinemaDataAccessMapper
                .bookingApprovalEntityToBookingApproval(bookingApprovalJpaRepository
                        .save(cinemaDataAccessMapper.bookingApprovalToBookingApprovalEntity(bookingApproval)));
    }

}
