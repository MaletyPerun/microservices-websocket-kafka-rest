package com.example.microservice2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@AllArgsConstructor
public class EventHandler extends TextWebSocketHandler implements WebSocketHandler {

//    @Autowired
//    public MessageService messageService;

    private static ProducerMessage producerMessage;


//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
//        System.out.println("Socket Connected: " + session);
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        session.sendMessage(new TextMessage("{ \"history\": [ \"ololo\", \"2\" ] }"));
        // TODO: 27.12.2022 здесь подключить обработку сообщения
        MessageDto received = JsonHelper.fromJson(message.getPayload(), MessageDto.class);
//        messageService.updateMessage(received);
        updateMessage(received);
//        MessageDto updated = updateMessage(created);

//        System.out.println("Received " + message.toString());
    }

    //    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        System.out.println("Socket Closed: [" + closeStatus.getCode() + "] " + closeStatus.getReason());
//        super.afterConnectionClosed(session, closeStatus);
//    }
    public static void updateMessage(MessageDto messageDto) {
//        Message updated = MessageUtil.dtoToMessage(messageDto);
        messageDto.setMC2_timestamp(TimeUtil.getDateTime());
        producerMessage.sendMessage(messageDto);
    }
}

