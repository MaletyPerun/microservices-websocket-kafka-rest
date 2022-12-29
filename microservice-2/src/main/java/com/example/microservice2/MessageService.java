package com.example.microservice2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final ProducerMessage producerMessage;

    public void updateMessage(MessageDto messageDto) {
//        Message updated = MessageUtil.dtoToMessage(messageDto);
        messageDto.setMC2_timestamp(TimeUtil.getDateTime());
        producerMessage.sendMessage(messageDto);
    }
}

