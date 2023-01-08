package com.example.microservice1.util;

import com.example.microservice1.model.Message;
import com.example.microservice1.dto.MessageDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class MessageUtil {

    public static Message dtoToMessage(MessageDto messageDto) {
        log.info("from dto to message = {}", messageDto);
        return new Message(messageDto.getId(),
                messageDto.getSession_id(),
                messageDto.getMC1_timestamp(),
                messageDto.getMC2_timestamp(),
                messageDto.getMC3_timestamp(),
                messageDto.getEnd_timestamp());
    }

    public static MessageDto messageToDto(Message message) {
        log.info("from message to dto = {}", message);
        return new MessageDto(message.getId(),
                message.getSession(),
                message.getMC1_timestamp(),
                message.getMC2_timestamp(),
                message.getMC3_timestamp(),
                message.getEnd_timestamp());
    }
}
