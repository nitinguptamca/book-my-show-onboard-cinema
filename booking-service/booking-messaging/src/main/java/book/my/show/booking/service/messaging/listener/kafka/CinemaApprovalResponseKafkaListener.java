package book.my.show.booking.service.messaging.listener.kafka;



import book.my.show.booking.service.domain.ports.input.message.listener.cinemaapproval.CinemaApprovalResponseMessageListener;
import book.my.show.booking.service.messaging.mapper.BookingMessagingDataMapper;
import book.my.show.kafka.booking.avro.model.BookingApprovalStatus;
import book.my.show.kafka.booking.avro.model.CinemaApprovalResponseAvroModel;
import book.my.show.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static book.my.show.booking.service.domain.constants.DomainConstants.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
public class CinemaApprovalResponseKafkaListener implements KafkaConsumer<CinemaApprovalResponseAvroModel> {

    private final CinemaApprovalResponseMessageListener cinemaApprovalResponseMessageListener;
    private final BookingMessagingDataMapper bookingMessagingDataMapper;

    public CinemaApprovalResponseKafkaListener(CinemaApprovalResponseMessageListener
                                                           cinemaApprovalResponseMessageListener,
                                               BookingMessagingDataMapper bookingMessagingDataMapper) {
        this.cinemaApprovalResponseMessageListener = cinemaApprovalResponseMessageListener;
        this.bookingMessagingDataMapper = bookingMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.Cinema-approval-consumer-group-id}",
                topics = "${booking-service.Cinema-approval-response-topic-name}")
    public void receive(@Payload List<CinemaApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of Cinema approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(cinemaApprovalResponseAvroModel -> {
            if (BookingApprovalStatus.APPROVED == cinemaApprovalResponseAvroModel.getBookingApprovalStatus()) {
                log.info("Processing approved booking for booking id: {}",
                        cinemaApprovalResponseAvroModel.getBookingId());
                cinemaApprovalResponseMessageListener.bookingApproved(bookingMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(cinemaApprovalResponseAvroModel));
            } else if (BookingApprovalStatus.REJECTED == cinemaApprovalResponseAvroModel.getBookingApprovalStatus()) {
                log.info("Processing rejected booking for booking id: {}, with failure messages: {}",
                        cinemaApprovalResponseAvroModel.getBookingId(),
                        String.join(FAILURE_MESSAGE_DELIMITER,
                                cinemaApprovalResponseAvroModel.getFailureMessages()));
                cinemaApprovalResponseMessageListener.bookingRejected(bookingMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(cinemaApprovalResponseAvroModel));
            }
        });

    }
}
