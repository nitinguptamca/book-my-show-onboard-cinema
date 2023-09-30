package book.my.show.kafka.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public <T> CompletableFuture<SendResult<String, T>>
    getKafkaCallback(String responseTopicName, T avroModel, String bookingId, String avroModelName) {
        return new CompletableFuture<SendResult<String ,T>>(){

        };
    }
}

