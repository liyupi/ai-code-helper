package com.yupi.aicodehelper.ai.model;

import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MiniMaxChatModelConfigTest {

    @Test
    void usesTheDefaultModelForBothChatClients() {
        MiniMaxChatModelConfig config = configForTest();
        assertEquals("https://api.minimax.io/v1", config.getBaseUrl());
        assertClientModel(config, "MiniMax-M3.1-Flash");
    }

    @Test
    void supportsAnAlternateModelAndRegionalEndpoint() {
        MiniMaxChatModelConfig config = configForTest();
        config.setModelName("MiniMax-M3");
        config.setBaseUrl("https://api.minimaxi.com/v1");
        assertEquals("https://api.minimaxi.com/v1", config.getBaseUrl());
        assertClientModel(config, "MiniMax-M3");
    }

    private MiniMaxChatModelConfig configForTest() {
        MiniMaxChatModelConfig config = new MiniMaxChatModelConfig();
        config.setApiKey("test-key");
        config.setChatModelListener(mock(ChatModelListener.class));
        return config;
    }

    private void assertClientModel(MiniMaxChatModelConfig config, String expectedModel) {
        OpenAiChatModel chat = (OpenAiChatModel) config.miniMaxChatModel();
        OpenAiStreamingChatModel streaming = (OpenAiStreamingChatModel) config.miniMaxStreamingChatModel();
        assertEquals(expectedModel, chat.defaultRequestParameters().modelName());
        assertEquals(expectedModel, streaming.defaultRequestParameters().modelName());
    }
}
