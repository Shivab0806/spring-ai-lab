package com.springai.service.impl;

import com.springai.dto.ChatRequest;
import com.springai.enums.AiProvider;
import com.springai.service.AIChatService;
import com.springai.service.AIMessageRoles;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OllamaChatService implements AIChatService, AIMessageRoles {

    private final ChatClient ollamaChatClient;


    public OllamaChatService(ChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }

    @Override
    public AiProvider getProvider() {
        return AiProvider.OLLAMA;
    }

    @Override
    public String chat(String prompt) {
        return ollamaChatClient.
                prompt(prompt)
                .call().content();
    }

    @Override
    public ChatResponse chat(ChatRequest chatRequest) {
        UserMessage userMessage= new UserMessage(chatRequest.message());

        SystemMessage systemMessage = new SystemMessage(
                "You are a Java programming assistant. " +
                        "You must answer only Java and Spring Boot related questions. " +
                        "If the user asks about politics, policies, history, or unrelated topics, " +
                        "politely refuse and explain that you only assist with Java and Spring Boot."
        );

        Prompt prompt= new Prompt(Arrays.asList(systemMessage,userMessage));

        return ollamaChatClient.
                prompt(prompt)
                .call().chatResponse();
    }

}
