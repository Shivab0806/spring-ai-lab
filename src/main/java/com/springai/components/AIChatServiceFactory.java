package com.springai.components;

import com.springai.enums.AiProvider;
import com.springai.service.AIChatService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AIChatServiceFactory {

    private Map<AiProvider, AIChatService> chatServiceMap;

    public AIChatServiceFactory(List<AIChatService> chatServiceList) {
        this.chatServiceMap = chatServiceList.stream().
                collect(Collectors.toMap(AIChatService::getProvider, Function.identity()));
    }

    public AIChatService getProvider(AiProvider aiProvider){
        return chatServiceMap.get(aiProvider);
    }

}
