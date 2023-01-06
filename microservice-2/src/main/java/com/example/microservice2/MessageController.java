package com.example.microservice2;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/MS2")
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/hello")
    public void greeting(MessageDto messageDto) throws Exception {
        Thread.sleep(1000); // simulated delay
    }


    @GetMapping("/test")
    public String test() {
//        MessageDto created = MessageDto.builder()
//                .id(56)
//                .session_id(234)
//                .MC1_timestamp(TimeUtil.getDateTime())
//                .build();
//        messageService.sendMessage(created);
//        log.info("test message with id = {}, session_id = {}", created.getId(), created.getSession_id());
//        return created.toString();

        return "It`s closed";
    }


    // TODO: 23.12.2022 правильно использовать socket в не-REST контроллере
//    private static final int PORT = 9000;
//
//    public void getDto() {
//        MessageDto messageDto = null;
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//
//            Socket socket = serverSocket.accept();
//
//            try (ObjectInputStream is = (ObjectInputStream) socket.getInputStream()) {
//                messageDto = (@Valid MessageDto) is.readObject();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        MessageDto updated = MessageService.updateMessage(messageDto);
//        // TODO: 23.12.2022 отправить через kafka
}

