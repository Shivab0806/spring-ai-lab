package com.springai.controller;

import com.springai.dto.ChatRequest;
import com.springai.service.GeminiAIChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gen/ai")
public class GeminiAIChatController {

    private final GeminiAIChatService geminiAIChatService;


    public GeminiAIChatController(GeminiAIChatService geminiAIChatService) {
        this.geminiAIChatService = geminiAIChatService;
    }

    @GetMapping("/chat")
    public  String chatWithGeminiLLM(@RequestBody ChatRequest chatRequest){
        return geminiAIChatService.chatWithGenAiLLM(chatRequest.message());
    }
}
