package org.example.springaiclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebClientConfig {
    private static final String baseUrl = "https://d5dc9tpogca5a0mflma8.zj2i1qoy.apigw.yandexcloud.net";
    private static final String apiKey = "sk-tFF7HWGR7MrlA5Hx6c1EIfhG6QyVZuNS";

    @Bean
    public WebClient openAiWebClient() {

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
