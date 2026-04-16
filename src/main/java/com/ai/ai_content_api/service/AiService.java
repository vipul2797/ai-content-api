package com.ai.ai_content_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(final ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generate(final String prompt) {
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}