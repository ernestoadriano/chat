package com.ernesto.chat.service;

import com.ernesto.chat.dto.MessageRequest;
import com.ernesto.chat.exception.NotFoundEntityException;
import com.ernesto.chat.model.Conversation;
import com.ernesto.chat.model.Message;
import com.ernesto.chat.model.User;
import com.ernesto.chat.repository.ConversationRepository;
import com.ernesto.chat.repository.MessageRepository;
import com.ernesto.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public Message sendMessage(MessageRequest messageRequest) {
        User sender = userRepository.findById(messageRequest.sender_id())
                .orElseThrow(NotFoundEntityException::new);
        Conversation conversation = conversationRepository.findById(messageRequest.conversation_id())
                .orElseThrow(NotFoundEntityException::new);

        Message message = new Message(conversation, sender, messageRequest.content());

        return messageRepository.save(message);
    }
}
