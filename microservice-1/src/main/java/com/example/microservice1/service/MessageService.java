package com.example.microservice1.service;

import com.example.microservice1.controller.MessageWebSocketController;
import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.model.Message;
import com.example.microservice1.repository.MessageRepository;
import com.example.microservice1.util.MessageUtil;
import com.example.microservice1.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    // TODO: 08.01.2023 https://devmark.ru/article/crud-repository
    private final MessageRepository messageRepository;
    private final MessageWebSocketController webSocketController;
    private static final AtomicInteger SESSION_ID = new AtomicInteger(0);
    private static LocalDateTime startTime;
    private static LocalDateTime endTime;
    @Value("${app.ms1.work.time}")
    private int workTimeInSec;
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

        if (endTime.isAfter(TimeUtil.getDateTime()) && flagWork) {
            sendMessageViaWebSocket(SESSION_ID.get());
        } else {
            stopWork();
            if (endTime.isBefore(TimeUtil.getDateTime())) {
                log.info("time is out");
            }
        }
        return saved;
    }

    public List<Message> getAll() {
        List<Message> list = messageRepository.findAll();
        log.info("get all messages from DB, amount = {}, messages = {}, ", list.size(), list);
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
        startTime = TimeUtil.getDateTime();
        endTime = startTime.plusSeconds(workTimeInSec);
        log.info("start time = {}", startTime);
        log.info("end time = {}", endTime);
        log.info("sec time of work = {}", workTimeInSec);
        flagWork = true;
        log.info("start work with session = {}", SESSION_ID.get());
        sendMessageViaWebSocket(SESSION_ID.get());
    }

    public String stopWork() {
        flagWork = false;
        int amount = messageRepository.findMessagesBySession(SESSION_ID.get()).size();
        long time = endTime.isBefore(TimeUtil.getDateTime()) ? workTimeInSec : ChronoUnit.SECONDS.between(startTime, TimeUtil.getDateTime());
        log.info("stop work with session = {}", SESSION_ID.get());
        log.info("time of work is = {} sec", time);
        log.info("amount messages of session = {}", messageRepository.findMessagesBySession(SESSION_ID.get()).size());
        return String.format("stop work. Work time = %d sec, amount of session messages = %d", time, amount);
    }
}

