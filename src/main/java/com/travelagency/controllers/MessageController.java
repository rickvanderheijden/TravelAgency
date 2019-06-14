package com.travelagency.controllers;

import com.travelagency.model.Message;
import com.travelagency.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<List<Message>> getAllMessagesByTravelGroupId(long id) {
        return Optional.of(messageRepository.findAllByTravelGroupTo(id));
    }

    public Optional<List<Message>> getAllMessagesByUserToId(long id) {
        return Optional.of(messageRepository.findAllByUserTo(id));
    }

    public Optional<List<Message>> getAllMessagesByUserFromId(long id) {
        return Optional.of(messageRepository.findAllByUserFrom(id));
    }

    public Optional<Message> addMessage(Message message) {
        return Optional.of(messageRepository.save(message));
    }
}
