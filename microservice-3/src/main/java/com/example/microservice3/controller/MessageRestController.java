package com.example.microservice3.controller;

import com.example.microservice3.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class MessageRestController {

    private final RestTemplate restTemplate;

    public void sendToMS1(MessageDto received) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<MessageDto> entity = new HttpEntity<>(received,headers);
        log.info("send dto on restTemplate = {}", received.toString());
        restTemplate.exchange(
                "http://localhost:53251/MS1/service", HttpMethod.POST, entity, String.class);

    }
}
