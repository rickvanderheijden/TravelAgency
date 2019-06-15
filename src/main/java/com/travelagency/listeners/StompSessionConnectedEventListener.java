package com.travelagency.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelagency.controllers.JwtController;
import com.travelagency.controllers.UserController;
import com.travelagency.controllers.WebSocketController;
import com.travelagency.model.User;
import com.travelagency.model.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Optional;

@Component
public class StompSessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StompSessionConnectedEventListener.class);

    private final JwtController jwtController;
    private final WebSocketController webSocketController;

    public StompSessionConnectedEventListener(JwtController jwtController, WebSocketController webSocketController) {
        this.jwtController = jwtController;
        this.webSocketController = webSocketController;
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectedEvent.getMessage());
        String header = headerAccessor.toString().substring(headerAccessor.toString().indexOf("token=["));
        String token = header.substring(7, header.indexOf("]"));

        Optional<User> userFromToken = this.jwtController.getUserFromToken(token);

        if(userFromToken.isPresent()) {
            UserStatus userStatus = new UserStatus(userFromToken.get().getId(), userFromToken.get().getUsername(), true);
            try {
                this.webSocketController.sendMessage(userStatus.toJson());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}