package com.example.microservice2;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class MessageController {

    @MessageMapping("/hello")
    public void greeting(MessageDto messageDto) throws Exception {
        Thread.sleep(1000); // simulated delay
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

