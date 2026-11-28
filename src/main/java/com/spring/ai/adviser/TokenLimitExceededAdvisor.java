package com.spring.ai.adviser;

import com.spring.ai.exception.TokenLimitExceedException;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.Objects;

public class TokenLimitExceededAdvisor implements CallAdvisor {

    Logger logger= LoggerFactory.getLogger(TokenLimitExceededAdvisor.class);

    private static final Integer TOTAL_LIMIT_TOKENS=2000;


    @Override
    public @NonNull ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        if (Objects.nonNull(chatClientResponse)) {
            ChatResponse chatResponse = chatClientResponse.chatResponse();
            if (Objects.nonNull(chatResponse) && Objects.nonNull(chatResponse.getMetadata())) {
                Usage usage = chatResponse.getMetadata().getUsage();
                if (Objects.nonNull(usage)) {
                    Long cacheReadInputTokens = usage.getCacheReadInputTokens();
                    Long cacheWriteInputTokens = usage.getCacheWriteInputTokens();
                    Integer completionTokens = usage.getCompletionTokens();
                    Integer promptTokens = usage.getPromptTokens();
                    Integer totalTokens = usage.getTotalTokens();
                    logger.info(
                            "Token Usage Summary -> Cache Read Tokens: {}, Cache Write Tokens: {}, Prompt Tokens: {}, Completion Tokens: {}, Total Tokens: {}",
                            cacheReadInputTokens,
                            cacheWriteInputTokens,
                            promptTokens,
                            completionTokens,
                            totalTokens
                    );
                    if (totalTokens > TOTAL_LIMIT_TOKENS) {
                        throw new TokenLimitExceedException(
                                "Token limit exceeded. Current tokens: " + totalTokens
                        );
                    }
                }
            }
        }

        return chatClientResponse;
    }

    @Override
    public @NonNull String getName() {
        return TokenLimitExceededAdvisor.class.getName();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
