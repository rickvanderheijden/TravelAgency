package com.travelagency.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelagency.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

@Controller
public class WebSocketController {

    private static final String SENDING_URL = "/topic/server-broadcaster";
    private static final String RECEIVING_URL = "/server-receiver";

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping(RECEIVING_URL)
    public void onReceivedMessage(String message) {
        System.out.println(message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Message message1 = mapper.readValue(message, Message.class);

//            this.messageController.addMessage(message1);

            sendMessage(message1.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New message received : ");
    }

    @SubscribeMapping(SENDING_URL)
    public void onSubscribe() {
//        return "SUBSCRIBED : " + message;
    }

    public void sendMessage(String message) {
        template.convertAndSend(SENDING_URL, buildNextMessage(message));
    }

    private String buildNextMessage(String message) {
        System.out.println("Send message: " + message);
        return message;
    }
}
