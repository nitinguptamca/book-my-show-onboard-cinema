package book.my.show.cinema.service.messaging.publisher.kafka;


import book.my.show.cinema.service.domain.config.CinemaServiceConfigData;
import book.my.show.cinema.service.domain.event.BookingApprovedEvent;
import book.my.show.cinema.service.domain.ports.output.message.publisher.BookingApprovedMessagePublisher;
import book.my.show.cinema.service.messaging.mapper.CinemaMessagingDataMapper;
import book.my.show.kafka.booking.avro.model.CinemaApprovalResponseAvroModel;
import book.my.show.kafka.producer.KafkaMessageHelper;
import book.my.show.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingApprovedKafkaMessagePublisher implements BookingApprovedMessagePublisher {

    private final CinemaMessagingDataMapper cinemaMessagingDataMapper;
    private final KafkaProducer<String, CinemaApprovalResponseAvroModel> kafkaProducer;
    private final CinemaServiceConfigData cinemaServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public BookingApprovedKafkaMessagePublisher(CinemaMessagingDataMapper cinemaMessagingDataMapper,
                                                KafkaProducer<String, CinemaApprovalResponseAvroModel> kafkaProducer,
                                                CinemaServiceConfigData cinemaServiceConfigData,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.cinemaMessagingDataMapper = cinemaMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.cinemaServiceConfigData = cinemaServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(BookingApprovedEvent bookingApprovedEvent) {
        String bookingId = bookingApprovedEvent.getBookingApproval().getBookingId().getValue().toString();

        log.info("Received BookingApprovedEvent for booking id: {}", bookingId);

        try {
            CinemaApprovalResponseAvroModel cinemaApprovalResponseAvroModel =
                    cinemaMessagingDataMapper
                            .bookingApprovedEventToCinemaApprovalResponseAvroModel(bookingApprovedEvent);

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
