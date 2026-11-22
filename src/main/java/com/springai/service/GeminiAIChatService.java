package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiAIChatService {

    private final ChatClient chatClient;


    public GeminiAIChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chatWithGenAiLLM(String message){
        return chatClient.
                prompt(message)
                .call().content();
    }
}
