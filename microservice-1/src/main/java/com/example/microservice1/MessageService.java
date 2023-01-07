package com.example.microservice1;

import com.example.microservice1.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public Message getMessage(int id) {

        // TODO: 07.01.2023 работает, когда нахожу из findAll, через getReferenceById - выдает ошибку: объект полностью не подгружается, поля пустые
//        return MessageUtil.messageToDto(messageRepository.getReferenceById(id));
//        Message mes = messageRepository.getReferenceById(id);
        List<Message> list = messageRepository.findAll();
        Message mesFromList = list.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        log.info("mesFromList = {}", mesFromList);
        log.info("mes MS1_time = {}", mesFromList.getMC1_timestamp());
        log.info("mes id = {}", mesFromList.getId());
        log.info("mes session = {}", mesFromList.getSession_id());
//        log.info("mes session_id = {}", mes.getSession_id());
//        return messageRepository.getReferenceById(id);
        return mesFromList;
    }

    public MessageDto createMessage(int sessionId) {
//        public MessageDto createMessage (sessionId) {
        Message message = new Message();
        message.setSession_id(sessionId);
        message.setMC1_timestamp(TimeUtil.getDateTime());
        Message created = messageRepository.saveAndFlush(message);

        log.info("received mes id = {}", message.getId());
        log.info("received mes session_id = {}", message.getSession_id());
        log.info("received mes time MS1 = {}", message.getMC1_timestamp());
        log.info("received mes time MS2 = {}", message.getMC2_timestamp());
        log.info("received mes time MS3 = {}", message.getMC3_timestamp());
        log.info("received mes time end = {}", message.getEnd_timestamp());
        // TODO: 23.12.2022 добавить проверку на id нового сообщения
        return MessageUtil.messageToDto(created);
//        return null;
    }

    public void saveMessage(MessageDto messageDto) {
        Message message = MessageUtil.dtoToMessage(messageDto);
        log.info("mes before save and flush = {}", message);
        messageRepository.saveAndFlush(message);
    }

    public void saveEndMessage(MessageDto messageDto) {
        Message message = MessageUtil.dtoToMessage(messageDto);
        message.setEnd_timestamp(TimeUtil.getDateTime());
        messageRepository.saveAndFlush(message);
//        log.info("received mes id = {}", message.getId());
        log.info("received mes id = {}", message.getId());
        log.info("received mes session_id = {}", message.getSession_id());
        log.info("received mes time MS1 = {}", message.getMC1_timestamp());
        log.info("received mes time MS2 = {}", message.getMC2_timestamp());
        log.info("received mes time MS3 = {}", message.getMC3_timestamp());
        log.info("received mes time end = {}", message.getEnd_timestamp());

        log.info("saved in DB mes = {}", message);
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }
}

