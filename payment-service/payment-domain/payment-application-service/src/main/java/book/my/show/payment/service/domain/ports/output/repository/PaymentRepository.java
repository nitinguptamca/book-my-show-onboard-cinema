package book.my.show.payment.service.domain.ports.output.repository;


import book.my.show.payment.service.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findByBookingId(UUID bookingId);
}
