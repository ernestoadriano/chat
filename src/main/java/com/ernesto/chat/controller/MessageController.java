package com.ernesto.chat.controller;

import com.ernesto.chat.dto.MessageRequest;
import com.ernesto.chat.model.Message;
import com.ernesto.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    

    @PostMapping("/message/send")
    public Message sendMessage(@RequestBody MessageRequest messageRequest) {
        return messageService.sendMessage(messageRequest);
    }
}
