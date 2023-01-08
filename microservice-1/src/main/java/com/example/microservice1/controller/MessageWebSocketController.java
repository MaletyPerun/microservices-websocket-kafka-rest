package com.example.microservice1.controller;

import com.example.microservice1.config.MyWebSocketHandler;
import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.util.JsonHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private static final String URI_WEBSOCKET = "ws://localhost:53252/events";

    private final MyWebSocketHandler webSocketHandler;

    public void sendMessageVidWebSocket(MessageDto newMessageDto) {

        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketSession session = null;
        String message = JsonHelper.toJson(newMessageDto);

        // TODO: 08.01.2023 ВОИ
        try {
            CompletableFuture<WebSocketSession> fut = client.execute(webSocketHandler, URI_WEBSOCKET);
            session = fut.get();
            session.sendMessage(new TextMessage(message));
            session.close();

        } catch (InterruptedException | IOException | ExecutionException e) {
            log.error("error with send via websocket = {}", e.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (IOException e) {
                log.error("error with closing websocket = {}", e.getMessage());
            }
        }
    }
}
