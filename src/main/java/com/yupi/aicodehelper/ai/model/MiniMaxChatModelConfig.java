package com.yupi.aicodehelper.ai.model;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * MiniMax LLM provider configuration.
 * <p>
 * Uses MiniMax's OpenAI-compatible API via langchain4j-open-ai.
 * Activated when {@code ai.provider=minimax} is set in application.yml.
 * </p>
 *
 * @see <a href="https://platform.minimaxi.com/document/guides/chat-model/pro/api">MiniMax API docs</a>
 */
@Configuration
@ConditionalOnProperty(name = "ai.provider", havingValue = "minimax")
@ConfigurationProperties(prefix = "minimax")
@Data
public class MiniMaxChatModelConfig {

    private String apiKey;

    private String modelName = "MiniMax-M2.7";

    private String baseUrl = "https://api.minimax.io/v1";

    @Resource
    private ChatModelListener chatModelListener;

    @Bean
    @Primary
    public ChatModel miniMaxChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .baseUrl(baseUrl)
                .temperature(0.7)
                .listeners(List.of(chatModelListener))
                .build();
    }

    @Bean
    @Primary
    public StreamingChatModel miniMaxStreamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .baseUrl(baseUrl)
                .temperature(0.7)
                .build();
    }
}
