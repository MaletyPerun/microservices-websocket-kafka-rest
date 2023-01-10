package com.example.microservice1.service;

import com.example.microservice1.controller.MessageWebSocketController;
import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.model.Message;
import com.example.microservice1.repository.MessageRepository;
import com.example.microservice1.util.MessageUtil;
import com.example.microservice1.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    // TODO: 08.01.2023 https://devmark.ru/article/crud-repository
    // обработка исключений с БД

    // TODO: 08.01.2023 тут же смотреть дублирование id и сессии (раздел добавление и удаление)
    // TODO: 08.01.2023 ВОИ

    private final MessageRepository messageRepository;

    private final MessageWebSocketController webSocketController;

    private static final AtomicInteger SESSION_ID = new AtomicInteger(0);

    private static boolean flagWork = false;

    public Message getMessage(int id) {
        Message getMessage = messageRepository.findMessageById(id);
        log.info("get message from DB = {}", getMessage);
        if (getMessage == null) {
            throw new NotFoundException(id);
        }
        return getMessage;
    }

    public MessageDto createMessage(int session) {
        Message buildMessage = Message.builder()
                .session(session)
                .MC1_timestamp(TimeUtil.getDateTime())
                .build();
        Message created = messageRepository.save(buildMessage);
        log.info("created and saved in DB message = {}", created);
        return MessageUtil.messageToDto(created);
    }

    public Message saveEndMessage(MessageDto received) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error with sleep = {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        Message endMessage = MessageUtil.dtoToMessage(received);
        endMessage.setEnd_timestamp(TimeUtil.getDateTime());
        Message saved = messageRepository.save(endMessage);
        if (flagWork) {
            sendMessageViaWebSocket(SESSION_ID.get());
        }
        return saved;
    }

    public List<Message> getAll() {
        List<Message> list = messageRepository.findAll();
        log.info("get all messages from DB = {}", list);
        if (list.isEmpty()) {
            throw new EmptyException("history is empty");
        }
        return messageRepository.findAll();
    }

    public void sendMessageViaWebSocket(int session) {
        MessageDto newMessageDto = createMessage(session);
        log.info("create and send new dto = {}", newMessageDto);
        webSocketController.sendMessageVidWebSocket(newMessageDto);
    }

    public void startWork() {
        SESSION_ID.getAndIncrement();
        flagWork = true;
        log.info("start work with session = {}", SESSION_ID.get());
        sendMessageViaWebSocket(SESSION_ID.get());
    }

    public void stopWork() {
        flagWork = false;
        log.info("stop work with session = {}", SESSION_ID.get());
    }
}

