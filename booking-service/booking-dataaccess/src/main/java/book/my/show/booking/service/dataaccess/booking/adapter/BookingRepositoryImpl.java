package book.my.show.booking.service.dataaccess.booking.adapter;


import book.my.show.booking.service.dataaccess.booking.mapper.BookingDataAccessMapper;
import book.my.show.booking.service.dataaccess.booking.repository.BookingJpaRepository;
import book.my.show.booking.service.domain.entity.Booking;
import book.my.show.booking.service.domain.ports.output.repository.BookingRepository;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingRepositoryImpl implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;
    private final BookingDataAccessMapper bookingDataAccessMapper ;

    public BookingRepositoryImpl(BookingJpaRepository bookingJpaRepository,
                                 BookingDataAccessMapper bookingDataAccessMapper) {
        this.bookingJpaRepository = bookingJpaRepository;
        this.bookingDataAccessMapper = bookingDataAccessMapper;
    }

    @Override
    public Booking save(Booking booking) {

        return bookingDataAccessMapper
                .bookingEntityToBooking(bookingJpaRepository
                        .save(bookingDataAccessMapper.bookingTobookingEntity(booking)));
    }

    @Override
    public Optional<Booking> findById(BookingId bookingId) {

        return bookingJpaRepository.findById(bookingId.getValue()).map(bookingDataAccessMapper::bookingEntityToBooking);
    }

    @Override
    public Optional<Booking> findByTrackingId(TrackingId trackingId) {
        return bookingJpaRepository.findByTrackingId(trackingId.getValue())
                .map(bookingDataAccessMapper::bookingEntityToBooking);
    }
}
