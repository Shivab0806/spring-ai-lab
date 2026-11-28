package com.spring.ai.components;

import com.spring.ai.enums.AiProvider;
import com.spring.ai.service.AIChatService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AIChatServiceFactory {

    private final Map<AiProvider, AIChatService> chatServiceMap;

    public AIChatServiceFactory(List<AIChatService> chatServiceList) {
        this.chatServiceMap = chatServiceList.stream().
                collect(Collectors.toMap(AIChatService::getProvider, Function.identity()));
    }

    public AIChatService getProvider(AiProvider aiProvider){
        return chatServiceMap.get(aiProvider);
    }

}
