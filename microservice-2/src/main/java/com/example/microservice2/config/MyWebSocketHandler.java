package com.example.microservice2.config;

import com.example.microservice2.dto.MessageDto;
import com.example.microservice2.service.MessageService;
import com.example.microservice2.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@AllArgsConstructor
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;

    @Override
    protected void handleTextMessage(@NotNull WebSocketSession session, TextMessage message) {
        MessageDto received = JsonHelper.fromJson(message.getPayload(), MessageDto.class);
        log.info("take dto from websocket in JSON = {}", received.toString());
        messageService.sendMessage(received);
    }
}
