package com.example.sample.advice;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Getter
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
