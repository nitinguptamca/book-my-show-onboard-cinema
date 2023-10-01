package book.my.show.booking.service.messaging.publisher.kafka;


import book.my.show.booking.service.domain.config.BookingServiceConfigData;
import book.my.show.booking.service.domain.event.BookingCreatedEvent;
import book.my.show.booking.service.domain.ports.output.message.publisher.payment.BookingCreatedPaymentRequestMessagePublisher;
import book.my.show.booking.service.messaging.mapper.BookingMessagingDataMapper;
import book.my.show.kafka.booking.avro.model.PaymentRequestAvroModel;
import book.my.show.kafka.producer.KafkaMessageHelper;
import book.my.show.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateBookingKafkaMessagePublisher implements BookingCreatedPaymentRequestMessagePublisher {

    private final BookingMessagingDataMapper bookingMessagingDataMapper;
    private final BookingServiceConfigData bookingServiceConfigData;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper bookingKafkaMessageHelper;

    public CreateBookingKafkaMessagePublisher(BookingMessagingDataMapper bookingMessagingDataMapper, 
                                              BookingServiceConfigData bookingServiceConfigData, 
                                              KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer, 
                                              KafkaMessageHelper bookingKafkaMessageHelper) {
        this.bookingMessagingDataMapper = bookingMessagingDataMapper;
        this.bookingServiceConfigData = bookingServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.bookingKafkaMessageHelper = bookingKafkaMessageHelper;
    }


    @Override
    public void publish(BookingCreatedEvent domainEvent) {
        String bookingId = domainEvent.getBooking().getId().getValue().toString();
        log.info("Received BookingCreatedEvent for booking id: {}", bookingId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel = bookingMessagingDataMapper
                    .bookingCreatedEventToPaymentRequestAvroModel(domainEvent);

            kafkaProducer.send(bookingServiceConfigData.getPaymentRequestTopicName(),
                    bookingId,
                    paymentRequestAvroModel,
                    bookingKafkaMessageHelper
                            .getKafkaCallback(bookingServiceConfigData.getPaymentResponseTopicName(),
                                    paymentRequestAvroModel,
                                    bookingId,
                                    "PaymentRequestAvroModel"));

            log.info("PaymentRequestAvroModel sent to Kafka for booking id: {}", paymentRequestAvroModel.getBookingId());
        } catch (Exception e) {
           log.error("Error while sending PaymentRequestAvroModel message" +
                   " to kafka with booking id: {}, error: {}", bookingId, e.getMessage());
        }
    }
}
