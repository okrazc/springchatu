package com.example.demo;

import com.example.demo.controller.ChatController;
import com.example.demo.model.ChatForm;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ChatController.class)
public class PostChatTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    void postChatMessage_ShouldAddMessage_AndReturnChatView() throws Exception {

        ChatForm chatForm = new ChatForm();
        chatForm.setUsername("TestUser");
        chatForm.setMessageText("TestMessage");
        ChatMessage chatMessage = new ChatMessage("TestUser", "TestMessage");

        when(messageService.getChatMessages()).thenReturn(List.of(chatMessage));

        mockMvc.perform(post("/chat")
                .param("username", chatForm.getUsername())
                .param("messageText", chatForm.getMessageText()))
                .andExpect(view().name("chat"))
                .andExpect(model().attributeExists("chatMessages"));

        verify(messageService).addMessage(any(ChatForm.class));

    }
}
