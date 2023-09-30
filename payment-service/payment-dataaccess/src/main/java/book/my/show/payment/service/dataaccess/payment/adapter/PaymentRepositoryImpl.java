package book.my.show.payment.service.dataaccess.payment.adapter;


import book.my.show.payment.service.dataaccess.payment.mapper.PaymentDataAccessMapper;
import book.my.show.payment.service.dataaccess.payment.repository.PaymentJpaRepository;
import book.my.show.payment.service.domain.entity.Payment;
import book.my.show.payment.service.domain.ports.output.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository,
                                 PaymentDataAccessMapper paymentDataAccessMapper) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.paymentDataAccessMapper = paymentDataAccessMapper;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentDataAccessMapper
                .paymentEntityToPayment(paymentJpaRepository
                        .save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Optional<Payment> findByBookingId(UUID bookingId) {
        return paymentJpaRepository.findByBookingId(bookingId)
                .map(paymentDataAccessMapper::paymentEntityToPayment);
    }
}
