package com.marcin.Facebook.message.controller;

import com.marcin.Facebook.message.model.DeleteMessageRequest;
import com.marcin.Facebook.message.model.Message;
import com.marcin.Facebook.message.model.AddMessageRequest;
import com.marcin.Facebook.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createMessage(@RequestBody AddMessageRequest addMessageRequest, @RequestParam Integer authorId){
        messageService.addMessage(addMessageRequest, authorId);
    }

    @GetMapping
    public List<Message> getMessagesByUserId(@RequestParam Integer authorId) {
        return messageService.getActiveMessageByUserId(authorId);
    }

    @PutMapping
    public void addReactionToMessage(@RequestParam Integer userReactionId, @RequestParam Integer messageId, @RequestParam Integer reactionId) {
        messageService.addReactionToMessage(userReactionId, messageId, reactionId);
    }

    @PutMapping("/delete")
    public void deleteMessage(@RequestBody DeleteMessageRequest request) {
        messageService.deleteMessage(request.getId());
    }
}
