package org.example.springaiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatApiService {
    private final WebClient webClient;

    public ChatApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ChatResponse> sendMessage(String request) {
        OpenAIRequest openAIRequest = createRequest(request);
        return webClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(openAIRequest)
                .retrieve()
                .bodyToMono(OpenAIResponse.class)
                .map(this::convertToChatResponse)
                .onErrorResume(e -> Mono.just(createErrorResponse(e.getMessage())));
    }

    private OpenAIRequest createRequest(String request) {
        OpenAIRequest openAIRequest = new OpenAIRequest();
        openAIRequest.setMessages(List.of(
            new OpenAIRequest.Message("system", "Ответь на русском языке"),
            new OpenAIRequest.Message("user", request)
        ));
        return openAIRequest;
    }

    private ChatResponse createErrorResponse(String error) {
        ChatResponse response = new ChatResponse();
        response.setStatus("error");
        response.setError(error != null ? error : "Unknown error");
        return response;
    }

    private ChatResponse convertToChatResponse(OpenAIResponse openAIResponse) {
        ChatResponse result = new ChatResponse();
        if (openAIResponse.getChoices()!=null && !openAIResponse.getChoices().isEmpty()){
            result.setStatus("success");
            result.setResponse(openAIResponse.getChoices().get(0).getMessage().getContent());
        }else{
            result = createErrorResponse("Empty response");
        }
        return result;
    }
}
