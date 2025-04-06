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
public class ChatController {
   private final ChatApiService chatApiService;

   public ChatController(ChatApiService chatApiService) {
       this.chatApiService = chatApiService;
   }

   @PostMapping("/chat")
    public Mono<ChatResponse> processMessage(@RequestBody ChatRequest chatRequest) {
       return chatApiService.sendMessage(chatRequest);
   }
}
