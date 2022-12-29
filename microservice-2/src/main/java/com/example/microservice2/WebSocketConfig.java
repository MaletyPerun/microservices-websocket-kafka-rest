package com.example.microservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
//@EnableWebSocketMessageBroker
@EnableWebSocket
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
public class WebSocketConfig implements WebSocketConfigurer {

//    https://zetcode.com/spring/websocket/
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/events");
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new EventHandler(), "/events")
//                .setAllowedOrigins("*")
//        //        .withSockJS()
//        ;
//    }

//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/topic");
//    }
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").withSockJS();
//    }

}
