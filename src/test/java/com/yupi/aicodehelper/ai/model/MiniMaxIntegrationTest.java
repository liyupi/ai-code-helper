package com.yupi.aicodehelper.ai.model;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for MiniMax LLM provider.
 * <p>
 * These tests require MINIMAX_API_KEY environment variable to be set.
 * Run with: MINIMAX_API_KEY=your-key mvn test -Dtest=MiniMaxIntegrationTest
 * </p>
 */
@EnabledIfEnvironmentVariable(named = "MINIMAX_API_KEY", matches = ".+")
class MiniMaxIntegrationTest {

    private static final String API_KEY = System.getenv("MINIMAX_API_KEY");
    private static final String BASE_URL = "https://api.minimax.io/v1";
    private static final String MODEL_NAME = "MiniMax-M3.1-Flash";

    @Test
    void testChatCompletion() {
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(MODEL_NAME)
                .baseUrl(BASE_URL)
                .temperature(0.7)
                .build();

        ChatResponse response = chatModel.chat(
                SystemMessage.from("You are a helpful coding assistant."),
                UserMessage.from("What is Java? Reply in one sentence.")
        );

        assertNotNull(response);
        assertNotNull(response.aiMessage());
        assertFalse(response.aiMessage().text().isEmpty());
    }

    @Test
    void testStreamingChatCompletion() throws Exception {
        StreamingChatModel streamingModel = OpenAiStreamingChatModel.builder()
                .apiKey(API_KEY)
                .modelName(MODEL_NAME)
                .baseUrl(BASE_URL)
                .temperature(0.7)
                .build();

        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuilder sb = new StringBuilder();

        streamingModel.chat("What is Spring Boot? Reply in one sentence.",
                new StreamingChatResponseHandler() {
                    @Override
                    public void onPartialResponse(String partialResponse) {
                        sb.append(partialResponse);
                    }

                    @Override
                    public void onCompleteResponse(ChatResponse completeResponse) {
                        String text = sb.toString();
                        if (text.isEmpty() && completeResponse.aiMessage() != null) {
                            text = completeResponse.aiMessage().text();
                        }
                        future.complete(text);
                    }

                    @Override
                    public void onError(Throwable error) {
                        future.completeExceptionally(error);
                    }
                });

        String result = future.get();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testAlternateModel() {
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName("MiniMax-M3")
                .baseUrl(BASE_URL)
                .temperature(0.7)
                .build();

        ChatResponse response = chatModel.chat(
                UserMessage.from("Say hello in Chinese.")
        );

        assertNotNull(response);
        assertNotNull(response.aiMessage());
        assertFalse(response.aiMessage().text().isEmpty());
    }
}
