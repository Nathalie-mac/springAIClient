package org.example.springaiclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatApiService {
    private final WebClient webClient;

    public ChatApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ChatResponse> sendMessage(ChatRequest request) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                //.onErrorReturn(createErrorResponse())
                /*.map(response -> {
                    if ("error".equals(response.getStatus())) {
                        throw new RuntimeException(response.getError());
                    }
                    return response;
                })*/
                .onErrorResume(e -> Mono.just(createErrorResponse(e.getMessage())));
    }

    private ChatResponse createErrorResponse(String error) {
        ChatResponse response = new ChatResponse();
        response.setStatus("error");
        response.setError(error != null ? error : "Unknown error");
        return response;
    }
}
