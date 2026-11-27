package com.springai.controller;

import com.springai.service.AIOrderSupportAssistant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gen/ai/order-support-assistant")
public class AIOrderSupportAssistantController {

    private final AIOrderSupportAssistant aiOrderSupportAssistant;

    public AIOrderSupportAssistantController(AIOrderSupportAssistant aiOrderSupportAssistant) {
        this.aiOrderSupportAssistant = aiOrderSupportAssistant;
    }

    @RequestMapping("/assist")
    public String assist(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String issueDescription) throws Exception {
        return aiOrderSupportAssistant.assist(customerName, orderId, issueDescription);
    }

}
