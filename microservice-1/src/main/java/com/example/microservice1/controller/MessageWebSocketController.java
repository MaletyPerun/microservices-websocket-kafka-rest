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
        String message = JsonHelper.toJson(newMessageDto);
        CompletableFuture<WebSocketSession> fut = client.execute(webSocketHandler, uriWebsocket);

        try (WebSocketSession session = fut.get()) {
            session.sendMessage(new TextMessage(message));
        } catch (IOException | ExecutionException e) {
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
