package com.ernesto.chat.controller;

import com.ernesto.chat.dto.ConversationRequest;
import com.ernesto.chat.model.Conversation;
import com.ernesto.chat.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/conversation/create")
    public ResponseEntity<?> createConversation(@RequestBody ConversationRequest conversationRequest) {
        return conversationService.createConversation(conversationRequest);
    }

    @GetMapping("/conversation/get")
    public List<Conversation> get() {
        return conversationService.get();
    }
}
