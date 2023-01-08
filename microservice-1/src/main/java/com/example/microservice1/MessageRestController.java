package com.example.microservice1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/MS1")
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {
    private final MessageService messageService;

    private static boolean FLAG_WORK = false;
    private static AtomicInteger sessionId = new AtomicInteger(0);


    @GetMapping("/message")
    public ResponseEntity<Message> getMessage(@RequestParam int id) {
//        MessageDto mes = messageService.getMessage(id);
        Message mes = messageService.getMessage(id);
        return ResponseEntity.ok(mes);
    }

    @GetMapping("/init")
    public String initMethod() {
        return "It`s work";
    }

    @GetMapping("/all")
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping("/check")
    public String checkMethod() {
        startWork();
        String status = sendNewMessage();
        stopWork();
        return status;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDto> saveMessage(@Valid @RequestBody MessageDto messageDto) throws InterruptedException {
        if (FLAG_WORK) {
            messageService.saveMessage(messageDto);
        }
        Thread.sleep(3000);
        sendNewMessage();
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("/START")
    public String start() {
        startWork();
        sendNewMessage();
        return "It`s work";
    }

    @GetMapping("/STOP")
    public String stop() {
        stopWork();
        return "It`s stopped";
    }

    private String sendNewMessage() {
        if (FLAG_WORK) {

//            messageController.sendNewMessage(sessionId.getAndIncrement());
            // TODO: 27.12.2022 правильно настроить запросы по ApiGateway и всем явно присвоить порты
            String uri = "ws://localhost:53252/events";

            StandardWebSocketClient client = new StandardWebSocketClient();
            WebSocketSession session = null;
//            MessageDto created = messageService.createMessage(sessionId.getAndIncrement());
            MessageDto created = messageService.createMessage(sessionId.get());
            String message = JsonHelper.toJson(created);

            try {
                // The socket that receives events
                EventHandler socket = new EventHandler();
                // Make a handshake with server
                CompletableFuture<WebSocketSession> fut = client.execute(socket, uri);
//                ListenableFuture<WebSocketSession> fut = client.doHandshake(socket, uri);
                // Wait for Connect
                session = fut.get();
                // Send a message
                session.sendMessage(new TextMessage(message));
                // Close session
                session.close();

            } catch (Throwable t) {
                t.printStackTrace(System.err);
            } finally {
                try {
                    if (session != null) {
                        session.close();
                    }
                } catch (IOException ignored) {
                }
            }
        }
        return "it`s work";
    }


//    localhost:53251/MS1/service
    @PostMapping("/service")
    public String takeMesFromMS3(@RequestBody MessageDto messageDto) {
        messageService.saveEndMessage(messageDto);
        sendNewMessage();
//        stopWork();
        return "It`s take in service";
    }

    // FIXME: 08.01.2023 test
    @GetMapping("/testSave")
    public ResponseEntity<Message> save() {
        return ResponseEntity.ok(messageService.saveMessageAndReturn());
    }

    // FIXME: 08.01.2023 test
    @GetMapping("/testGet")
    public ResponseEntity<Message> getTest(@RequestParam int id) {
        return ResponseEntity.ok(messageService.getTestMes(id));
    }

    private void stopWork() {
        FLAG_WORK = false;
    }

    private void startWork() {
        sessionId.getAndIncrement();
        FLAG_WORK = true;
    }

}
