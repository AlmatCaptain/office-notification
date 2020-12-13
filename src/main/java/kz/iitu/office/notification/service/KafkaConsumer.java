package kz.iitu.office.notification.service;

import kz.iitu.office.notification.model.ReservedRoomDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "reserve-room-topic", groupId = "my-group")
    public void consume(ReservedRoomDTO roomDTO) throws AddressException {
        System.out.println("KafkaConsumer.consume-------------------------------------------------------------");
        EmailService.sendByEmail(roomDTO);
    }
}
