package com.example.microservice1;

import com.example.microservice1.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageDto getMessage(int id) {
        return new MessageDto();
    }

    public MessageDto createMessage(int sessionId) {
//        public MessageDto createMessage (sessionId) {
        Message message = new Message();
        message.setSession_id(sessionId);
        message.setMC1_timestamp(TimeUtil.getDateTime());
        Message created = messageRepository.saveAndFlush(message);
        // TODO: 23.12.2022 добавить проверку на id нового сообщения
        return MessageUtil.messageToDto(created);
//        return null;
    }

    public void saveMessage(MessageDto messageDto) {
        Message message = MessageUtil.dtoToMessage(messageDto);
        messageRepository.saveAndFlush(message);
    }

    public void saveEndMessage(MessageDto messageDto) {
        Message message = MessageUtil.dtoToMessage(messageDto);
        message.setEnd_timestamp(TimeUtil.getDateTime());
        messageRepository.saveAndFlush(message);
        log.info("saved in DB mes = {}", message);
    }
}

