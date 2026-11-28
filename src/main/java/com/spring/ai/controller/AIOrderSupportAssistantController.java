package com.spring.ai.controller;

import com.spring.ai.service.AIOrderSupportAssistant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("gen/ai/order-support-assistant")
public class AIOrderSupportAssistantController {

    private final AIOrderSupportAssistant aiOrderSupportAssistant;

    public AIOrderSupportAssistantController(AIOrderSupportAssistant aiOrderSupportAssistant) {
        this.aiOrderSupportAssistant = aiOrderSupportAssistant;
    }

    @GetMapping(path = "/assist", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> assist(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String issueDescription) throws Exception {
        return aiOrderSupportAssistant.assist(customerName, orderId, issueDescription);
    }

}
