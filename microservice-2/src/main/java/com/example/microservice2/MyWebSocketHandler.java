package com.example.microservice2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalTime;

@Component
@AllArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

//        var clientMessage = message.getPayload();
        MessageDto received = JsonHelper.fromJson(message.getPayload(), MessageDto.class);
        System.out.println(received.getId());
        System.out.println(received.getSession_id());
        System.out.println(received.getMC1_timestamp());
        messageService.updateMessage(received);
//        if (clientMessage.startsWith("hello") || clientMessage.startsWith("greet")) {
//            session.sendMessage(new TextMessage("Hello there!"));
//        } else if (clientMessage.startsWith("time")) {
//            var currentTime = LocalTime.now();
//            session.sendMessage(new TextMessage(currentTime.toString()));
//        } else {
//
//            session.sendMessage(new TextMessage("Unknown command"));
//        }
    }
}
