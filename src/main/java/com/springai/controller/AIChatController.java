package com.springai.controller;

import com.springai.dto.ChatRequest;
import com.springai.enums.AiProvider;
import com.springai.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("gen/ai")
public class AIChatController {

    private final AIService service;


    public AIChatController(AIService service) {
        this.service = service;
    }

    @GetMapping("/chat")
    public  String chatWithGeminiLLM(@RequestBody ChatRequest chatRequest){
        AiProvider aiProvider =AiProvider.valueOf(chatRequest.modelType().toUpperCase(Locale.ROOT));
        return service.chat(aiProvider,chatRequest.message());
    }
}
