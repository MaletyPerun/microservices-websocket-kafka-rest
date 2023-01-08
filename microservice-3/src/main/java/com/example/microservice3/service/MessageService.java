package com.example.microservice3.service;

import com.example.microservice3.util.TimeUtil;
import com.example.microservice3.controller.MessageRestController;
import com.example.microservice3.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MessageService {

    private final MessageRestController messageController;

    public void sendToMS1(MessageDto received) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error with sleep = {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        received.setMC3_timestamp(TimeUtil.getDateTime());
        messageController.sendToMS1(received);
    }
}
