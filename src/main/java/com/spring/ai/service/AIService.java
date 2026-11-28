package com.spring.ai.service;

import com.spring.ai.components.AIChatServiceFactory;
import com.spring.ai.enums.AiProvider;
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
