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
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

//        var clientMessage = message.getPayload();
        log.info("session = {}", session.getId());
        log.info("message = {}", message.getPayload());
        log.info("message.getPayload() = {}", message.getPayload());

        MessageDto received = JsonHelper.fromJson(message.getPayload(), MessageDto.class);
        messageService.sendMessage(received);

        log.info("received mes id = {}", received.getId());
        log.info("received mes session_id = {}", received.getSession_id());
        log.info("received mes time MS1 = {}", received.getMC1_timestamp());
        log.info("received mes time MS2 = {}", received.getMC2_timestamp());
        log.info("received mes time MS3 = {}", received.getMC3_timestamp());
        log.info("received mes time end = {}", received.getEnd_timestamp());

//        System.out.println(received.getId());
//        System.out.println(received.getSession_id());
//        System.out.println(received.getMC1_timestamp());
//        messageService.updateMessage(received);
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
