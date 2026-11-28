package com.spring.ai.controller;

import com.spring.ai.dto.ChatRequest;
import com.spring.ai.enums.AiProvider;
import com.spring.ai.service.AIMessageRoles;
import com.spring.ai.service.AIService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("gen/ai")
public class AIChatController {

    private final AIService service;
    private final AIMessageRoles aiMessageService;

    public AIChatController(AIService service, AIMessageRoles aiMessageService) {
        this.service = service;
        this.aiMessageService = aiMessageService;
    }

    @GetMapping("/chat")
    public  String chatWithGeminiLLM(@RequestBody ChatRequest chatRequest){
        AiProvider aiProvider =AiProvider.valueOf(chatRequest.modelType().toUpperCase(Locale.ROOT));
        return service.chat(aiProvider,chatRequest.message());
    }

    @GetMapping("/message")
    public ChatResponse getAiMessageService(@RequestBody ChatRequest chatRequest) {
        return aiMessageService.chat(chatRequest);
    }
}
