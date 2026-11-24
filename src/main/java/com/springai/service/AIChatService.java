package com.springai.service;

import com.springai.enums.AiProvider;

public interface AIChatService {

    AiProvider getProvider();

    String chat(String prompt);
}
