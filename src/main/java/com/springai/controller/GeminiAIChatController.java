package com.springai.controller;

import com.springai.service.GeminiAIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gen/ai")
public class GeminiAIChatController {

    private final GeminiAIChatService geminiAIChatService;


    public GeminiAIChatController(GeminiAIChatService geminiAIChatService) {
        this.geminiAIChatService = geminiAIChatService;
    }

    @GetMapping("/chat")
    public  String chatWithGeminiLLM(@RequestParam String message){
        return geminiAIChatService.chatWithGenAiLLM(message);
    }
}
