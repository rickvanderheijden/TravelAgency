package com.travelagency.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelagency.model.Message;
import com.travelagency.model.User;
import com.travelagency.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserController userController;
    private final  WebSocketController webSocketController;

    public MessageController(MessageRepository messageRepository, UserController userController, WebSocketController webSocketController) {
        this.messageRepository = messageRepository;
        this.userController = userController;
        this.webSocketController = webSocketController;
    }

    public Optional<List<Message>> getAllMessagesByTravelGroupId(long id) {
        return Optional.of(messageRepository.findAllByTravelGroupTo(id));
    }

    public Optional<List<Message>> getAllMessagesByUserToId(long id) {
        return Optional.of(messageRepository.findAllByReceiverId(id));
    }

    public Optional<List<Message>> getAllMessagesByUserFromId(long id) {
        return Optional.of(messageRepository.findAllBySender_Id(id));
    }

    public Optional<Message> addMessage(Message message) throws JsonProcessingException {
        if(message == null) return Optional.empty();

        User user = message.getSender();
        Optional<User> optionalUser = this.userController.getUserById(user.getId());

        if(!optionalUser.isPresent()){
            return Optional.empty();
        }
        message.setSender(optionalUser.get());
        Message savedMessage = messageRepository.saveAndFlush(message);
        this.webSocketController.sendMessage(message.toJson());
        return Optional.of(savedMessage);
    }
}
