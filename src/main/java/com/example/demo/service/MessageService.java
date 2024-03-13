package com.example.demo.service;

import com.example.demo.model.ChatForm;
import com.example.demo.model.ChatMessage;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class MessageService {
    List<ChatMessage> chatMessages = new ArrayList<>();

    public void addMessage(ChatForm chatForm) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say" :
                chatMessage.setMessage(chatForm.getMessageText());
                break;
            case "Shout" :
                chatMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                chatMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;
        }
        this.chatMessages.add(chatMessage);
    }

}
