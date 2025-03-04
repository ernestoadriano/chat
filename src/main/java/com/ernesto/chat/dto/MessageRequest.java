package com.ernesto.chat.dto;

public record MessageRequest(String content, String sender_id, String conversation_id) {
}
