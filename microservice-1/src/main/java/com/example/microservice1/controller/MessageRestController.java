package com.example.microservice1.controller;

import com.example.microservice1.dto.MessageDto;
import com.example.microservice1.model.Message;
import com.example.microservice1.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/MS1")
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {

    private final MessageService messageService;

    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> start() {
        messageService.startWork();
        log.info("start resending messages");
        return ResponseEntity.ok("It`s work!");
    }

    @GetMapping("/stop")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> stop() {
        return ResponseEntity.ok(messageService.stopWork());
    }

    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Message> getMessage(@RequestParam int id) {
        Message mes = messageService.getMessage(id);
        log.info("get message = {}", mes);
        return ResponseEntity.ok(mes);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Message>> getAll() {
        log.info("get all messages");
        return ResponseEntity.ok(messageService.getAll());
    }


    @PostMapping("/service")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> takeDtoFromMS3(@RequestBody @Valid @NotNull MessageDto received) {
        log.info("take dto from MS3 = {}", received);
        return ResponseEntity.ok(messageService.saveEndMessage(received));
    }
}
