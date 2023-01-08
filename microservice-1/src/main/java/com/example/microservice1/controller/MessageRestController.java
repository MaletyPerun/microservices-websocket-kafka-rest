package com.example.microservice1.controller;

import com.example.microservice1.service.MessageService;
import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MS1")
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {

    // TODO: 08.01.2023 везде возвращать ResponseEntity
    // TODO: 08.01.2023 ВОИ
    private final MessageService messageService;

    @GetMapping("/START")
    public String start() {
        messageService.startWork();
        log.info("start resending messages");
        return "It`s work";
    }

    @GetMapping("/STOP")
    public String stop() {
        messageService.stopWork();
        log.info("stop resending messages");
        return "It`s stopped";
    }

    @GetMapping("/message")
    public ResponseEntity<Message> getMessage(@RequestParam int id) {
        Message mes = messageService.getMessage(id);
        log.info("get message = {}", mes);
        return ResponseEntity.ok(mes);
    }

    @GetMapping("/all")
    public List<Message> getAll() {
        log.info("get all messages");
        return messageService.getAll();
    }


//    localhost:53251/MS1/service
    @PostMapping("/service")
    public void takeDtoFromMS3(@RequestBody MessageDto received) {
        log.info("take dto from MS3 = {}", received);
        messageService.saveEndMessage(received);
    }
}
