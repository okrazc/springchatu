package com.example.demo.model;

import lombok.Data;

@Data
public class ChatForm {
    private ChatMessage chatMessage;
    private String username;
    private String messageText;
    private String messageType;
}
