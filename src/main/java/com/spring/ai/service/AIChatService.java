package com.spring.ai.service;

import com.spring.ai.enums.AiProvider;

public interface AIChatService {

    AiProvider getProvider();

    String chat(String prompt);
}
