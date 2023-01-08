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

    // TODO: 08.01.2023 https://devmark.ru/article/crud-repository
    // обработка исключений с БД

    // TODO: 08.01.2023 тут же смотреть дублирование id и сессии (раздел добавление и удаление)

    private final MessageRepository messageRepository;

    public Message getMessage(int id) {

        // TODO: 07.01.2023 работает, когда нахожу из findAll, через getReferenceById - выдает ошибку: объект полностью не подгружается, поля пустые
//        return MessageUtil.messageToDto(messageRepository.getReferenceById(id));
//        Message mes = messageRepository.getReferenceById(id);
//        List<Message> list = messageRepository.findAll();
//        Message mesFromList = list.stream()
//                .filter(x -> x.getId() == id)
//                .findFirst()
//                .orElse(null);
        Message getMessage = messageRepository.findMessageById(id);
        log.info("mesFromList = {}", getMessage);
        log.info("mes MS1_time = {}", getMessage.getMC1_timestamp());
        log.info("mes id = {}", getMessage.getId());
        log.info("mes session = {}", getMessage.getSession_id());
//        log.info("mes session_id = {}", mes.getSession_id());
//        return messageRepository.getReferenceById(id);
        return getMessage;
    }

    public MessageDto createMessage(int sessionId) {
//        public MessageDto createMessage (sessionId) {
        Message message = new Message();
        message.setSession_id(sessionId);
        message.setMC1_timestamp(TimeUtil.getDateTime());
        Message created = messageRepository.save(message);

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
        log.info("mes before save = {}", message);
        messageRepository.save(message);
    }

    // FIXME: 08.01.2023 test
    public Message saveMessageAndReturn() {
//        Message testMes = new Message();
//        MessageDto messageDto = MessageUtil.messageToDto(testMes);
//        log.info("testMes before save = {}", messageDto);
        Message testMes = messageRepository.save(new Message());
        log.info("test mes = {}", testMes);
        return messageRepository.save(testMes);
    }

    public void saveEndMessage(MessageDto messageDto) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("error with sleep = {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        Message message = MessageUtil.dtoToMessage(messageDto);
        message.setEnd_timestamp(TimeUtil.getDateTime());
        messageRepository.save(message);
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

    public Message getTestMes(int id) {
        return messageRepository.findMessageById(id);
    }
}

