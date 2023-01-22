package com.example.microservice1.service;

import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.model.Message;
import com.example.microservice1.repository.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.microservice1.service.TestData.SESSION;
import static com.example.microservice1.service.TestData.getInitMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageRepository.save(getInitMessage());
    }

    @AfterEach
    void tearDown() {
        messageRepository.deleteAll();
    }

    @Test
    void getMessage() {
        Message messageInDB = messageRepository.save(getInitMessage());
        int id = messageInDB.getId();
        Message getMessageFromDB = messageService.getMessage(id);
        assertEquals(id, getMessageFromDB.getId());
    }

    @Test
    void createMessage() {
        MessageDto createdMessageDto = messageService.createMessage(SESSION);
        assertEquals(SESSION, createdMessageDto.getSession());
    }
}