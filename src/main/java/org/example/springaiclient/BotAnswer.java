package org.example.springaiclient;

import lombok.*;

@Data
public class BotAnswer {
    String response;
    String status;

    public BotAnswer(String response, String status) {
        this.response = response;
        this.status = status;
    }
    public BotAnswer(){}

}
