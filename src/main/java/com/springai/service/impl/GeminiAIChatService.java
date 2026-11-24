package com.springai.service.impl;

import com.springai.enums.AiProvider;
import com.springai.service.AIChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiAIChatService implements AIChatService {

    private final ChatClient geminiAIChatClient;


    public GeminiAIChatService(ChatClient geminiAIChatClient) {
        this.geminiAIChatClient = geminiAIChatClient;
    }

    @Override
    public AiProvider getProvider() {
        return AiProvider.GEMINI;
    }

    @Override
    public String chat(String prompt) {
        return geminiAIChatClient.
                prompt(prompt)
                .call().content();
    }
}
