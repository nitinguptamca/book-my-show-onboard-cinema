package book.my.show.booking.service.messaging.publisher.kafka;


import book.my.show.booking.service.domain.config.BookingServiceConfigData;
import book.my.show.booking.service.domain.event.BookingPaidEvent;
import book.my.show.booking.service.domain.ports.output.message.publisher.cinemaapproval.BookingPaidCinemaRequestMessagePublisher;
import book.my.show.booking.service.messaging.mapper.BookingMessagingDataMapper;
import book.my.show.kafka.booking.avro.model.CinemaApprovalRequestAvroModel;
import book.my.show.kafka.producer.KafkaMessageHelper;
import book.my.show.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayBookingKafkaMessagePublisher implements BookingPaidCinemaRequestMessagePublisher{

    private final BookingMessagingDataMapper bookingMessagingDataMapper;
    private final BookingServiceConfigData bookingServiceConfigData;
    private final KafkaProducer<String, CinemaApprovalRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper bookingKafkaMessageHelper;

    public PayBookingKafkaMessagePublisher(BookingMessagingDataMapper bookingMessagingDataMapper, 
                                           BookingServiceConfigData bookingServiceConfigData, 
                                           KafkaProducer<String, CinemaApprovalRequestAvroModel> kafkaProducer, 
                                           KafkaMessageHelper bookingKafkaMessageHelper) {
        this.bookingMessagingDataMapper = bookingMessagingDataMapper;
        this.bookingServiceConfigData = bookingServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.bookingKafkaMessageHelper = bookingKafkaMessageHelper;
    }


    @Override
    public void publish(BookingPaidEvent domainEvent) {
        String bookingId = domainEvent.getBooking().getId().getValue().toString();

        try {
            CinemaApprovalRequestAvroModel cinemaApprovalRequestAvroModel =
                    bookingMessagingDataMapper.bookingPaidEventToCinemaApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(bookingServiceConfigData.getCinemaApprovalRequestTopicName(),
                    bookingId,
                    cinemaApprovalRequestAvroModel,
                    bookingKafkaMessageHelper
                            .getKafkaCallback(


                                    bookingServiceConfigData.getCinemaApprovalRequestTopicName(),
                                    cinemaApprovalRequestAvroModel,
                                    bookingId,
                                    "CinemaApprovalRequestAvroModel"));

            log.info("CinemaApprovalRequestAvroModel sent to kafka for booking id: {}", bookingId);
        } catch (Exception e) {
            log.error("Error while sending CinemaApprovalRequestAvroModel message" +
                    " to kafka with booking id: {}, error: {}", bookingId, e.getMessage());
        }
    }
}
