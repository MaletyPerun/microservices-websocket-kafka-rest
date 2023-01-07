package com.example.microservice1;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@UtilityClass
@Slf4j
public class MessageUtil {

    public static Message dtoToMessage(MessageDto messageDto) {
        return new Message(messageDto.getId(),
                messageDto.getSession_id(),
                messageDto.getMC1_timestamp(),
                messageDto.getMC2_timestamp(),
                messageDto.getMC3_timestamp(),
                messageDto.getEnd_timestamp());
    }

    public static MessageDto messageToDto(Message message) {
        log.info("message session_id = {}", message.getSession_id());
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSession_id(message.getSession_id());
        messageDto.setMC1_timestamp(message.getMC1_timestamp());
        messageDto.setMC2_timestamp(message.getMC2_timestamp());
        messageDto.setMC3_timestamp(message.getMC3_timestamp());
        messageDto.setEnd_timestamp(message.getEnd_timestamp());
        log.info("it`s make a dto and send to controller, message = {}", messageDto.getId());
        return messageDto;
    }
}
