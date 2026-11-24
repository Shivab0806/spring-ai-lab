package com.springai.service.impl;

import com.springai.enums.AiProvider;
import com.springai.service.AIChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatService implements AIChatService {

    private final ChatClient ollamaChatClient;


    public OllamaChatService(ChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }

    @Override
    public AiProvider getProvider() {
        return AiProvider.OLLAMA;
    }

    @Override
    public String chat(String prompt) {
        return ollamaChatClient.
                prompt(prompt)
                .call().content();
    }
}
