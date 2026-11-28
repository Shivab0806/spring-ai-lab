package com.spring.ai.service;

import com.spring.ai.dto.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;

public interface AIMessageRoles {

    ChatResponse chat(ChatRequest chatRequest);
}
