package com.springai.service;

import com.springai.dto.ChatRequest;
import org.springframework.ai.chat.model.ChatResponse;

public interface AIMessageRoles {

    ChatResponse chat(ChatRequest chatRequest);
}
