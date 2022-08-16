package com.example.sample.payload.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private String message;

    @Builder
    public MessageResponse(String message) {
        this.message = message;
    }
}
