package com.example.microservice1.controller;

import com.example.microservice1.config.MyWebSocketHandler;
import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.util.JsonHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageWebSocketController {
    @Value("${app.connections.ms2.websocket.uri}")
    private String uriWebsocket;

    private final MyWebSocketHandler webSocketHandler;

    public void sendMessageVidWebSocket(MessageDto newMessageDto) {

        StandardWebSocketClient client = new StandardWebSocketClient();
//        WebSocketSession session = null;
        String message = JsonHelper.toJson(newMessageDto);
        CompletableFuture<WebSocketSession> fut = client.execute(webSocketHandler, uriWebsocket);


        try (WebSocketSession session = fut.get()) {
            session.sendMessage(new TextMessage(message));
        } catch (IOException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // TODO: 08.01.2023 ВОИ
//        try {
////            CompletableFuture<WebSocketSession> fut = client.execute(webSocketHandler, uriWebsocket);
//            // FIXME: 17.01.2023 заменить метод извлечения сессии
////            session = fut.get();
////            session.sendMessage(new TextMessage(message));
////            session.close();
//
//        } catch (InterruptedException | IOException | ExecutionException e) {
//            log.error("error with send via websocket = {}", e.getMessage());
//        } finally {
//            try {
//                if (session != null) {
//                    session.close();
//                }
//            } catch (IOException e) {
//                log.error("error with closing websocket = {}", e.getMessage());
//            }
//        }
    }
}
