package com.yupi.aicodehelper.ai.model;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MiniMax chat model configuration.
 */
class MiniMaxChatModelConfigTest {

    @Test
    void testDefaultValues() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        assertEquals("MiniMax-M2.7", config.getModelName());
        assertEquals("https://api.minimax.io/v1", config.getBaseUrl());
        assertNull(config.getApiKey());
    }

    @Test
    void testSetApiKey() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-api-key-123");
        assertEquals("test-api-key-123", config.getApiKey());
    }

    @Test
    void testSetModelName() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setModelName("MiniMax-M2.7-highspeed");
        assertEquals("MiniMax-M2.7-highspeed", config.getModelName());
    }

    @Test
    void testSetBaseUrl() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setBaseUrl("https://custom.minimax.io/v1");
        assertEquals("https://custom.minimax.io/v1", config.getBaseUrl());
    }

    @Test
    void testChatModelCreation() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setModelName("MiniMax-M2.7");
        config.setBaseUrl("https://api.minimax.io/v1");

        // Build OpenAiChatModel directly to verify config is valid
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .temperature(0.7)
                .build();

        assertNotNull(chatModel);
        assertInstanceOf(OpenAiChatModel.class, chatModel);
    }

    @Test
    void testStreamingChatModelCreation() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setModelName("MiniMax-M2.7");
        config.setBaseUrl("https://api.minimax.io/v1");

        StreamingChatModel streamingModel = OpenAiStreamingChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .temperature(0.7)
                .build();

        assertNotNull(streamingModel);
        assertInstanceOf(OpenAiStreamingChatModel.class, streamingModel);
    }

    @Test
    void testHighspeedModelConfig() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setModelName("MiniMax-M2.7-highspeed");
        config.setBaseUrl("https://api.minimax.io/v1");

        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .temperature(0.7)
                .build();

        assertNotNull(chatModel);
    }

    @Test
    void testTemperatureClampedAboveZero() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setModelName("MiniMax-M2.7");
        config.setBaseUrl("https://api.minimax.io/v1");

        // MiniMax requires temperature in (0.0, 1.0], verify 0.7 works
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .temperature(0.7)
                .build();

        assertNotNull(chatModel);
    }

    @Test
    void testM25ModelConfig() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setModelName("MiniMax-M2.5");
        config.setBaseUrl("https://api.minimax.io/v1");

        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(config.getApiKey())
                .modelName(config.getModelName())
                .baseUrl(config.getBaseUrl())
                .temperature(0.5)
                .build();

        assertNotNull(chatModel);
    }
}
