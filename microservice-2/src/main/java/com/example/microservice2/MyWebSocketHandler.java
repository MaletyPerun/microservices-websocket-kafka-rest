package com.example.microservice2;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        MessageDto received = JsonHelper.fromJson(message.getPayload(), MessageDto.class);
        messageService.sendMessage(received);
    }
}
