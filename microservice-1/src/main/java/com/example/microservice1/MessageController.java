package com.example.microservice1;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/activity")
    public MessageDto sendNewMessage(int andIncrement) {
        return messageService.createMessage(andIncrement);
    }

}
