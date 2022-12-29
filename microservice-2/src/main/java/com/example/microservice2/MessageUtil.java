package com.example.microservice2;

import lombok.experimental.UtilityClass;

@UtilityClass
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
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSession_id(message.getSession_id());
        messageDto.setMC1_timestamp(message.getMC1_timestamp());
        messageDto.setMC2_timestamp(message.getMC2_timestamp());
        messageDto.setMC3_timestamp(message.getMC3_timestamp());
        messageDto.setEnd_timestamp(message.getEnd_timestamp());
        return messageDto;
    }
}
