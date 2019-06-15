package com.travelagency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {

    private static final String SENDING_URL = "/topic/server-broadcaster";
    private static final String RECEIVING_URL = "/server-receiver";

    private final SimpMessagingTemplate template;

    private String message = "";

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping(RECEIVING_URL)
    public void onReceivedMessage(String message) {
        System.out.println("New message received : " + message);
    }

    @SubscribeMapping(SENDING_URL)
    public String onSubscribe() {
        return "SUBSCRIBED : " + message;
    }

    public void sendMessage(String message) {
        template.convertAndSend(SENDING_URL, buildNextMessage(message));
    }

    private String buildNextMessage(String message) {
        System.out.println("Send message: " + message);
        return message;
    }
}
