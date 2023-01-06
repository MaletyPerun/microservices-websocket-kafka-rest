package com.example.microservice3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class MessageRestController {

    @Autowired
    private final RestTemplate restTemplate;

//    public void sendToMS1(MessageDto messageDto) {
//        String URL = "localhost:53251/MS1/service";
//
//        HttpEntity<MessageDto> requestBody = new HttpEntity<>(messageDto);
//
//        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, requestBody, String.class);
//
//        log.info("response message = {}", response);
//    }

    public String sendToMS1(MessageDto messageDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<MessageDto> entity = new HttpEntity<>(messageDto,headers);

        return restTemplate.exchange(
                "http://localhost:53251/MS1/service", HttpMethod.POST, entity, String.class).getBody();
    }
}
