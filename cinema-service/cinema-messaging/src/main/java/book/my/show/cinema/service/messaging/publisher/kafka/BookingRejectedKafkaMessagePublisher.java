package book.my.show.cinema.service.messaging.publisher.kafka;

import book.my.show.cinema.service.domain.config.CinemaServiceConfigData;
import book.my.show.cinema.service.domain.event.BookingRejectedEvent;
import book.my.show.cinema.service.domain.ports.output.message.publisher.BookingRejectedMessagePublisher;
import book.my.show.cinema.service.messaging.mapper.CinemaMessagingDataMapper;

import book.my.show.kafka.booking.avro.model.CinemaApprovalResponseAvroModel;
import book.my.show.kafka.producer.KafkaMessageHelper;
import book.my.show.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingRejectedKafkaMessagePublisher implements BookingRejectedMessagePublisher {

    private final CinemaMessagingDataMapper cinemaMessagingDataMapper;
    private final KafkaProducer<String, CinemaApprovalResponseAvroModel> kafkaProducer;
    private final CinemaServiceConfigData cinemaServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public BookingRejectedKafkaMessagePublisher(CinemaMessagingDataMapper cinemaMessagingDataMapper,
                                                KafkaProducer<String, CinemaApprovalResponseAvroModel> kafkaProducer,
                                                CinemaServiceConfigData cinemaServiceConfigData,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.cinemaMessagingDataMapper = cinemaMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.cinemaServiceConfigData = cinemaServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(BookingRejectedEvent bookingRejectedEvent) {
        String bookingId = bookingRejectedEvent.getBookingApproval().getBookingId().getValue().toString();

        log.info("Received BookingRejectedEvent for booking id: {}", bookingId);

        try {
            CinemaApprovalResponseAvroModel cinemaApprovalResponseAvroModel =
                    cinemaMessagingDataMapper
                            .bookingRejectedEventToCinemaApprovalResponseAvroModel(bookingRejectedEvent);

            kafkaProducer.send(cinemaServiceConfigData.getCinemaApprovalResponseTopicName(),
                    bookingId,
                    cinemaApprovalResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(cinemaServiceConfigData
                                    .getCinemaApprovalResponseTopicName(),
                            cinemaApprovalResponseAvroModel,
                            bookingId,
                            "CinemaApprovalResponseAvroModel"));

            log.info("CinemaApprovalResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            log.error("Error while sending CinemaApprovalResponseAvroModel message" +
                    " to kafka with booking id: {}, error: {}", bookingId, e.getMessage());
        }
    }

}
