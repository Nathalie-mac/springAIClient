package org.example.springaiclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
public class BotController {
    private final WebClient webClient;

    public BotController(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping(value ="/bot",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BotAnswer> sendToBot(@RequestBody BotRequest botRequest) {
        return webClient.post()
                .uri("/bot")
                .bodyValue(botRequest)
                .retrieve()
                .bodyToMono(BotAnswer.class)
                .onErrorReturn(new BotAnswer());
    }

}
