package com.spring.ai.service;

import reactor.core.publisher.Flux;

public interface AIOrderSupportAssistant {

     Flux<String> assist(String customerName, String orderId, String issueDescription) throws Exception;
}

