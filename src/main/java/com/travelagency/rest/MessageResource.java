package com.travelagency.rest;

import com.travelagency.controllers.MessageController;
import com.travelagency.model.Message;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageResource {

    private final MessageController messageController;

    public MessageResource(MessageController messageController) {
        this.messageController = messageController;
    }

    @RequestMapping(value = "/travelgroup/{id}" , method = RequestMethod.GET)
    public Optional<List<Message>> getMessagesByTravelGroup(@PathVariable final Long id){
        return messageController.getAllMessagesByTravelGroupId(id);
    }

    @RequestMapping(value = "/userfrom/{id}" , method = RequestMethod.GET)
    public Optional<List<Message>> getMessagesByUserFrom(@PathVariable final Long id){
        return messageController.getAllMessagesByUserFromId(id);
    }

    @RequestMapping(value = "/userto/{id}" , method = RequestMethod.GET)
    public Optional<List<Message>> getMessagesByUserTo(@PathVariable final Long id){
        return messageController.getAllMessagesByUserToId(id);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Optional<Message> addMessage(@Valid @RequestBody Message message) {
        return messageController.addMessage(message);
    }
}
