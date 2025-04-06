package org.example.springaiclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BotRequest {
    boolean directRequest = true;
    String text;
    String userId;
}
