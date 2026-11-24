package com.springai.service;

import com.springai.components.AIChatServiceFactory;
import com.springai.enums.AiProvider;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final AIChatServiceFactory aiChatServiceFactory;

    public AIService(AIChatServiceFactory aiChatServiceFactory) {
        this.aiChatServiceFactory = aiChatServiceFactory;
    }

    public String chat(AiProvider aiProvider,String prompt){
       AIChatService aiChatService= aiChatServiceFactory.getProvider(aiProvider);
        return  aiChatService.chat(prompt);
    }
}
