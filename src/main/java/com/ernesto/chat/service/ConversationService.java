package com.ernesto.chat.service;

import com.ernesto.chat.dto.ConversationRequest;
import com.ernesto.chat.exception.NotFoundEntityException;
import com.ernesto.chat.model.Conversation;
import com.ernesto.chat.model.User;
import com.ernesto.chat.repository.ConversationRepository;
import com.ernesto.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createConversation(ConversationRequest conversationRequest) {
        if (conversationRequest.user1().equals(conversationRequest.user2()))
            return ResponseEntity.badRequest().body("Same id.");

        User user1 = userRepository.findById(conversationRequest.user1())
                .orElseThrow(NotFoundEntityException::new);

        User user2 = userRepository.findById(conversationRequest.user2())
                .orElseThrow(NotFoundEntityException::new);

        Conversation conversation = new Conversation(user1, user2);
        return ResponseEntity.ok(conversationRepository.saveAndFlush(conversation));
    }

    public List<Conversation> get() {
        return conversationRepository.findAll();
    }

}
